package com.axa.datamodel.Benchmark;


import com.axa.datamodel.CampaignTracker.CampaignTracker;
import com.axa.datamodel.CampaignTracker.CampaignTrackerRecord;
import com.axa.datamodel.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Benchmark {
    private List<BenchMarkRecord> benchMarkRecords;
    private File benchmarkFile;

    public Benchmark(File benchmarkFile) {
        this.benchmarkFile = benchmarkFile;
        this.benchMarkRecords = readBenchMarkRecordsFromPath(benchmarkFile.toPath());
    }

    private List<BenchMarkRecord> readBenchMarkRecordsFromPath(Path benchmarkPath) {
        List<BenchMarkRecord> records = new LinkedList<>();
        try  {
            BufferedReader br = Files.newBufferedReader(benchmarkPath, StandardCharsets.UTF_8);
            // read the first line from the text file
            String header = br.readLine();
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",", -1);
                BenchMarkRecord benchmark = BenchMarkRecord.createBenchmark(attributes);
                records.add(benchmark);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            System.out.println("No existing benchmark file, create new one...");
            return new LinkedList<>();
        }
        return records;
    }

    public Optional<BenchMarkRecord> findBenchmark(String campaignNature, String mediaBuy) {
        List<BenchMarkRecord> matched = this.benchMarkRecords.stream()
                .filter(d -> d.getMediaBuy().equalsIgnoreCase(mediaBuy) && d.getCampaignNature().equalsIgnoreCase(campaignNature))
                .collect(Collectors.toList());
        if (matched.size() != 1) return Optional.empty();
        return Optional.of(matched.get(0));
    }

    public String getFileName() {
        return this.benchmarkFile.getName();
    }

    public File getAbsoluteFile() {
        return this.benchmarkFile.getAbsoluteFile();
    }

    public String getAbsolutePath() {
        return this.benchmarkFile.getAbsolutePath();
    }

    public void updateBenchmark(List<CampaignTracker> allCampaignTrackers) {
        System.out.print("Updating benchmark...\n");

        // calculate new benchmark
        HashMap<Pair<String, String>, BenchMarkRecord> benchMarkHashMap = new HashMap<>();
        List<CampaignTrackerRecord> campaignTrackerRecords = new LinkedList<>();
        allCampaignTrackers.forEach(d -> campaignTrackerRecords.addAll(d.getCampaignRecords()));
        campaignTrackerRecords.forEach(campaignTrackerRecord -> {
            Pair key = new Pair(campaignTrackerRecord.getCampaignNature(), campaignTrackerRecord.getMediaBuy());
            BenchMarkRecord currentBenchmarkRecord;
            if (benchMarkHashMap.containsKey(key)) {
                currentBenchmarkRecord = benchMarkHashMap.get(key);
            } else {
                currentBenchmarkRecord = new BenchMarkRecord(campaignTrackerRecord.getCampaignNature(), campaignTrackerRecord.getMediaBuy(),
                        0, campaignTrackerRecord.getCPM().orElse(0.0000),
                        campaignTrackerRecord.getCPV().orElse(0.0000),
                        campaignTrackerRecord.getCTR().orElse(0.0000),
                        campaignTrackerRecord.getCPC().orElse(0.0000),
                        campaignTrackerRecord.getCPE().orElse(0.0000));
                benchMarkHashMap.put(key, currentBenchmarkRecord);
            }
            currentBenchmarkRecord.updateRecord(campaignTrackerRecord);
        });

        List<BenchMarkRecord> newBenchmark = new LinkedList<>();
        benchMarkHashMap.forEach((key, value) -> {
            BenchMarkRecord benchMarkRecord = benchMarkHashMap.get(key);
            benchMarkRecord.calcAndSetAverageValues();
            newBenchmark.add(benchMarkRecord);
        });

        this.benchMarkRecords = newBenchmark;
        writeBenchmarkToFile();
    }

    private void writeBenchmarkToFile() {
        System.out.printf("Updating benchmark: "+ benchmarkFile.getAbsolutePath());

        try {
            // input the (modified) file content to the StringBuffer "input"
            StringBuffer inputBuffer = new StringBuffer();
            inputBuffer.append(BenchMarkRecord.HEADER);
            benchMarkRecords.forEach(d -> inputBuffer.append(d.toCSVRowString()));

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(benchmarkFile.getAbsoluteFile());
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
            System.out.printf("Conslidated Report %s is updated with %d campaigns\n", benchmarkFile.toPath(), benchMarkRecords.size());

        } catch (Exception e) {
            System.out.println("Problem writing file." + e.getMessage());
        }
    }

    private static void reportMetrics(CampaignTrackerRecord campaignTrackerRecord) {
        System.out.printf("Metrics for this campaign for %s - %s\n", campaignTrackerRecord.getCampaignNature(), campaignTrackerRecord.getMediaBuy());
        System.out.printf("  CPN: %f ", campaignTrackerRecord.getCPM());
        System.out.printf("  CPV: %f ", campaignTrackerRecord.getCPV());
        System.out.printf("  CTR: %f ", campaignTrackerRecord.getCTR());
        System.out.printf("  CPC: %f ", campaignTrackerRecord.getCPC());
        System.out.printf("  CPE: %f \n", campaignTrackerRecord.getCPE());
    }

    private static void reportImprovedBenchmark(CampaignTrackerRecord campaignTrackerRecord) {
        System.out.printf("set improvement compared to campaignTracker for %s - %s\n", campaignTrackerRecord.getCampaignNature(), campaignTrackerRecord.getMediaBuy());
        System.out.printf(" Average CPM: %f ", campaignTrackerRecord.getImprovementInCPM());
        System.out.printf(" Average CPV: %f ", campaignTrackerRecord.getImprovementInCPV());
        System.out.printf(" Average CTR: %f ", campaignTrackerRecord.getImprovementInCTR());
        System.out.printf(" Average CPC: %f ", campaignTrackerRecord.getImprovementInCPC());
        System.out.printf(" Average CPE: %f \n\n\n", campaignTrackerRecord.getImprovementInCPE());
    }

    private static void reportBenchmark(BenchMarkRecord benchMarkRecord) {
        System.out.printf("accessing benchmark for %s - %s\n", benchMarkRecord.getCampaignNature(), benchMarkRecord.getMediaBuy());
        System.out.printf(" Average CPC: %f ", benchMarkRecord.getAverageCPM());
        System.out.printf(" Average CPC: %f ", benchMarkRecord.getAverageCPV());
        System.out.printf(" Average CPC: %f ", benchMarkRecord.getAverageCTR());
        System.out.printf(" Average CPC: %f ", benchMarkRecord.getAverageCPC());
        System.out.printf(" Average CPC: %f \n", benchMarkRecord.getAverageCPE());
    }

}

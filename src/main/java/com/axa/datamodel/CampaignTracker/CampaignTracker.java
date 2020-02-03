package com.axa.datamodel.CampaignTracker;


import com.axa.datamodel.Benchmark.BenchMarkRecord;
import com.axa.datamodel.Benchmark.Benchmark;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CampaignTracker {
    private List<CampaignTrackerRecord> campaignTrackerRecords;
    private String campaignName;
    private File filePath;

    public CampaignTracker(File file) {
        this.filePath = file;
        this.campaignTrackerRecords = readCampaignReport(file.toPath());
        this.campaignName = file.getName().replaceAll(".csv", "");
        ;
    }

    private static List<CampaignTrackerRecord> readCampaignReport(Path path) {
        List<CampaignTrackerRecord> reports = new LinkedList<>();
        try  {
            BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            // read the first line from the text file
            String header = br.readLine();
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                CampaignTrackerRecord campaignTrackerRecord = CampaignTrackerRecord.createCampaignTracker(attributes);
                reports.add(campaignTrackerRecord);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return reports;
    }

    public CampaignTrackerRecord getTotalCampaignRecord() {
        return this.campaignTrackerRecords.get(this.campaignTrackerRecords.size()-1);
    }

    public void setImprovementByBenchmark(Benchmark benchmark) {
        System.out.printf("Calculating improved benchmark for campaign %S \n",  this.campaignName);
        this.campaignTrackerRecords = campaignTrackerRecords.stream().peek(campaignTrackerRecord -> {
            String campaignNature = campaignTrackerRecord.getCampaignNature();
            String mediaBuy = campaignTrackerRecord.getMediaBuy();
            Optional<BenchMarkRecord> optionalBenchMarkRecord = benchmark.findBenchmark(campaignNature, mediaBuy);
            if (optionalBenchMarkRecord.isPresent()) {
                BenchMarkRecord benchMarkRecord = optionalBenchMarkRecord.get();
                campaignTrackerRecord.calcAndSetImprovement(benchMarkRecord);
            } else {
                System.out.printf("No valid bench mark found for %s - %s\n", mediaBuy, campaignNature);
            }
        }).collect(Collectors.toList());
    }


    public void moveToArchiveFolder(String newPath) {
        try {
            // input the (modified) file content to the StringBuffer "input"
            StringBuffer inputBuffer = new StringBuffer();
            inputBuffer.append(CampaignTrackerRecord.HEADER);
            campaignTrackerRecords.forEach(d -> inputBuffer.append(d.toCSVRowString()));

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(newPath + File.separator + this.filePath.getName());
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
            if (this.filePath.delete()) {
                System.out.printf("File %s is moved to %s\n", this.filePath.getName(), newPath);
            }
        } catch (Exception e) {
            System.out.println("\nProblem writing file.");
        }
    }


    public String getCampaignName() {
        return this.campaignName;
    }

    public List<CampaignTrackerRecord> getCampaignRecords() {
        return this.campaignTrackerRecords;
    }
}

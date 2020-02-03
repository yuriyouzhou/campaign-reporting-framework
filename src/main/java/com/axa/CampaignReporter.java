package com.axa;

import com.axa.datamodel.Benchmark.Benchmark;
import com.axa.datamodel.CampaignTracker.CampaignTracker;
import com.axa.datamodel.ConsolidatedReport.ConsolidatedReport;
import com.axa.datamodel.ConsolidatedReport.ConsolidatedReportRecord;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class CampaignReporter {

    public static void main(String[] args){
        File archiveCampaignsDir = new File(args[0]);
        File newCampaignDir = new File(args[1]);
        File consolidatedDir = new File(args[2]);
        File benchmarkPath = new File(args[3]);


        // 1. update rates and total values in individual campaign files if applicable
        List<CampaignTracker> campaignTrackers = readCampaignFilesFromPath(newCampaignDir);
        if (campaignTrackers.size() == 0) {
            System.out.println("No new campaign files found. Exit.");
            System.exit(0);
        }

        Benchmark benchMark = new Benchmark(benchmarkPath);
        for (CampaignTracker campaignTracker : campaignTrackers) {
            campaignTracker.setImprovementByBenchmark(benchMark);
            campaignTracker.moveToArchiveFolder(archiveCampaignsDir.getAbsolutePath());
        }
        // 2. generate consolidated results
        List<CampaignTracker> allCampaignTrackers = readCampaignFilesFromPath(archiveCampaignsDir);
        List<ConsolidatedReportRecord> consolidatedReportRecords = ConsolidatedReport.generateConsolidatedReportRecords(allCampaignTrackers);
        ConsolidatedReport consolidatedReport = new ConsolidatedReport(consolidatedDir);
        consolidatedReport.setRecords(consolidatedReportRecords);
        consolidatedReport.updateReport();

        // 3. update bench mark
        benchMark.updateBenchmark(allCampaignTrackers);


    }

    private static List<CampaignTracker> readCampaignFilesFromPath(File dirP) {
        System.out.println("Finding campaigns records from "+ dirP.getAbsolutePath());
        File[] files = dirP.listFiles();
        List<CampaignTracker> campaignTrackers = new LinkedList<>();
        assert files != null;
        for (File file : files) {
            if (file.getName().contains("csv")) {
                CampaignTracker campaignTracker = new CampaignTracker(file);
                campaignTrackers.add(campaignTracker);
            }
        }
        return campaignTrackers;
    }




}



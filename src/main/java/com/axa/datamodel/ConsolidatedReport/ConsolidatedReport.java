package com.axa.datamodel.ConsolidatedReport;


import com.axa.datamodel.CampaignTracker.CampaignTracker;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsolidatedReport {
    private final File consolidatedFile;
    private List<ConsolidatedReportRecord> consolidatedReportRecords;

    public ConsolidatedReport(File consolidatedFile) {
        this.consolidatedFile = consolidatedFile;
        this.consolidatedReportRecords = new LinkedList<>();
    }

    public static List<ConsolidatedReportRecord> generateConsolidatedReportRecords(List<CampaignTracker> campaignTrackers) {
        List<ConsolidatedReportRecord> consolidatedReportRecords = campaignTrackers.stream()
                .map(d -> ConsolidatedReportRecord.generateFromCampaignTracker(d.getCampaignName(), d.getTotalCampaignRecord()))
                .collect(Collectors.toList());
        return consolidatedReportRecords;
    }

    public void updateReport() {
        try {
            // input the (modified) file content to the StringBuffer "input"

            StringBuffer inputBuffer = new StringBuffer();
            inputBuffer.append(ConsolidatedReportRecord.HEADER);
            consolidatedReportRecords.forEach(d -> inputBuffer.append(d.toCSVRowString()));

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(consolidatedFile.getAbsoluteFile());
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
            System.out.printf("Conslidated Report %s is updated with %d campaigns\n", consolidatedFile.getName(), consolidatedReportRecords.size());

        } catch (Exception e) {
            System.out.println("\nProblem writing file." + e.getCause());
        }
    }

    public void setRecords(List<ConsolidatedReportRecord> consolidatedReportRecords) {
        this.consolidatedReportRecords = consolidatedReportRecords;
    }
}

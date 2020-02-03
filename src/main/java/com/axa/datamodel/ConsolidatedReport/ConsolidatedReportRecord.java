package com.axa.datamodel.ConsolidatedReport;


import com.axa.datamodel.CampaignTracker.CampaignTrackerRecord;

import java.util.Optional;
import java.util.StringJoiner;

import static com.axa.StringUtil.StringUtil.*;


public class ConsolidatedReportRecord {
    public static String HEADER = "Campaign name,Campaign nature,Start date,End date,Primary KPI,Budget,Impressions,CPM,Improvement in CPM,Video views,CPV,Improvement in CPV,CTR,Improvement in CTR,Clicks,CPC,Improvement in CPC,Reactions,Shares,Comments,Total no of engagements,CPE,Improvement in CPE\n";
    private String campaignName;
    private String campaignNature;
    private String startDate;
    private String endDate;
    private String primaryKPI;
    private Optional<Double> budget;
    private Optional<Integer> impressions;
    private Optional<Double> CPM;
    private Optional<Double> improvementInCPM;
    private Optional<Integer> videoViews;
    private Optional<Double> CPV;
    private Optional<Double> improvementInCPV;
    private Optional<Double> CTR;
    private Optional<Double> improvementInCTR;
    private Optional<Integer> clicks;
    private Optional<Double> CPC;
    private Optional<Double> improvementInCPC;
    private Optional<Integer> reactions;
    private Optional<Integer> shares;
    private Optional<Integer> comments;
    private Optional<Integer> totalNumberOfEngagement;
    private Optional<Double> CPE;
    private Optional<Double> improvementInCPE;

    public ConsolidatedReportRecord(String campaignName, String campaignNature, String startDate, String endDate,
                                    String primaryKPI,
                                    Optional<Double> budget, Optional<Integer> impressions,
                                    Optional<Double> cpm, Optional<Double> improvementInCPM, Optional<Integer> videoViews,
                                    Optional<Double> cpv, Optional<Double> improvementInCPV, Optional<Double> ctr,
                                    Optional<Double> improvementInCTR, Optional<Integer> clicks, Optional<Double> cpc,
                                    Optional<Double> improvementInCPC, Optional<Integer> reactions, Optional<Integer> shares,
                                    Optional<Integer> comments, Optional<Integer> totalNumberOfEngagement, Optional<Double> cpe,
                                    Optional<Double> improvementInCPE) {
        this.campaignName = campaignName;
        this.campaignNature = campaignNature;
        this.startDate = startDate;
        this.endDate = endDate;
        this.primaryKPI = primaryKPI;
        this.budget = budget;
        this.impressions = impressions;
        this.CPM = cpm;
        this.improvementInCPM = improvementInCPM;
        this.videoViews = videoViews;
        this.CPV = cpv;
        this.improvementInCPV = improvementInCPV;
        this.CTR = ctr;
        this.improvementInCTR = improvementInCTR;
        this.clicks = clicks;
        this.CPC = cpc;
        this.improvementInCPC = improvementInCPC;
        this.reactions = reactions;
        this.shares = shares;
        this.comments = comments;
        this.totalNumberOfEngagement = totalNumberOfEngagement;
        this.CPE = cpe;
        this.improvementInCPE = improvementInCPE;

    }


    public static ConsolidatedReportRecord generateFromCampaignTracker(String campaignName, CampaignTrackerRecord total) {
        return new ConsolidatedReportRecord(
        campaignName, total.getCampaignNature(),
                total.getStartDate(),
                total.getEndDate(),
                total.getPrimaryKPI(),
                total.getBudget(),
                total.getImpressions(),
                total.getCPM(),
                total.getImprovementInCPM(),
                total.getVideoViews(),
                total.getCPV(),
                total.getImprovementInCPV(),
                total.getCTR(),
                total.getImprovementInCTR(),
                total.getClicks(),
                total.getCPC(),
                total.getImprovementInCPC(),
                total.getReactions(),
                total.getShares(),
                total.getComments(),
                total.getTotalNumberOfEngagement(),
                total.getCPE(),
                total.getImprovementInCPE());
    }


    public String toCSVRowString() {
        StringJoiner stringJoiner = new StringJoiner(",");
        stringJoiner.add(campaignName).add(campaignNature)
                .add(startDate)
                .add(endDate)
                .add(primaryKPI)
                .add(getDollarString(budget))
                .add(getIntString(impressions))
                .add(getDollarString(CPM))
                .add(getPercentageString(improvementInCPM))
                .add(getIntString(videoViews))
                .add(getDoubleString(CPV))
                .add(getPercentageString(improvementInCPV))
                .add(getPercentageString(CTR))
                .add(getPercentageString(improvementInCTR))
                .add(getIntString(clicks))
                .add(getDollarString(CPC))
                .add(getPercentageString(improvementInCPC))
                .add(getIntString(reactions))
                .add(getIntString(shares))
                .add(getIntString(comments))
                .add(getIntString(totalNumberOfEngagement))
                .add(getDollarString(CPE))
                .add(getPercentageString(improvementInCPE)).add("\n");
        return stringJoiner.toString();    }

}

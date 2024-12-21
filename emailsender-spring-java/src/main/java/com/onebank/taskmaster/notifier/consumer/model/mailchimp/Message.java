package com.onebank.taskmaster.notifier.consumer.model.mailchimp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.onebank.taskmaster.notifier.consumer.model.To;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Message {
	private String html;
	private String text;
	private String subject;
	private String fromEmail;
	private String publishFromEmail;
	private String fromName;
	private String publishFromName;
	private List<To> to = new ArrayList<>();
	private Headers headers;
	private boolean important;
	private boolean trackOpens;
	private boolean trackClicks;
	private boolean autoText;
	private boolean autoHtml;
	private boolean inlineCss;
	private boolean urlStripQs;
	private boolean preserveRecipients;
	private boolean viewContentLink;
	private String bccAddress;
	private String trackingDomain;
	private String signingDomain;
	private String returnPathDomain;
	private boolean merge;
	private String mergeLanguage;
	private List<GlobalMergeVar> globalMergeVars = new ArrayList<>();
	private List<MergeVars> mergeVars = new ArrayList<>();
	private List<String> tags = new ArrayList<>();
	@JsonProperty("subaccount")
	private String subaccount;
	private List<String> googleAnalyticsDomains = new ArrayList<>();
	private String googleAnalyticsCampaign;
	private Metadata metadata;
	private List<RecipientMetadata> recipientMetadata = new ArrayList<>();
	private List<Attachment> attachments = new ArrayList<>();
	private List<Image> images = new ArrayList<>();
}

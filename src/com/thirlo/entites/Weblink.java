package com.thirlo.entites;

import com.thirilo.partner.Shareable;

public class Weblink extends BookMark implements Shareable {
	private String url;
	private String host;
	private String htmlPage;
	private DownloadStaus downloadStatus = DownloadStaus.NOT_ATTEMPTED;

	public enum DownloadStaus {
		NOT_ATTEMPTED, SUCCESS, FAILED, NOT_ELIGIBLE;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHtmlPage() {
		return htmlPage;
	}

	public void setHtmlPage(String htmlPage) {
		this.htmlPage = htmlPage;
	}

	@Override
	public String toString() {
		return "WebLink [url=" + url + ", host=" + host + "]";
	}

	@Override
	public boolean isKidFriendlyEligible() {
		try {

			if (url.contains("porn") || getTitle().contains("porn") || host.contains("adult")) {
				return false;
			}
		} catch (NullPointerException e) {
		}
		return true;

	}

	@Override
	public String getItemData() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		builder.append("<item>");
		builder.append("<type>Weblink</type>");
		builder.append("<title>").append(getTitle()).append("</title>");
		builder.append("<url>").append(url).append("</url>");
		builder.append("<host>").append(host).append("</host>");
		builder.append("</item>");

		return builder.toString();
	}

	public DownloadStaus getDownloadStatus() {
		return downloadStatus;
	}

	public void setDownloadStatus(DownloadStaus downloadStatus) {
		this.downloadStatus = downloadStatus;
	}

}

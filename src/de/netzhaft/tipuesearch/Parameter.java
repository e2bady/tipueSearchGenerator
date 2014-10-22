package de.netzhaft.tipuesearch;

public class Parameter {
	private final String titleSelector;
	private final String descSelector;
	private final String contentSelector;
	private final String tagSelector;
	
	private final String foldername;
	private final String outputfile;
	
	public static Parameter parse(String[] args) {
		String titleSelector = args[0];
		String descSelector = args[1];
		String contentSelector = args[2];
		String tagSelector = args[3];
		
		String foldername = args[4];
		String outputfile = args[5];
		
		return new Parameter(titleSelector, descSelector, contentSelector, tagSelector, foldername, outputfile);
	}
	
	public Parameter(String titleSelector, String descSelector,
			String contentSelector, String tagSelector, String foldername,
			String outputfile) {
		super();
		this.titleSelector = titleSelector;
		this.descSelector = descSelector;
		this.contentSelector = contentSelector;
		this.tagSelector = tagSelector;
		this.foldername = foldername;
		this.outputfile = outputfile;
	}

	public String getTitleSelector() {
		return titleSelector;
	}

	public String getDescSelector() {
		return descSelector;
	}

	public String getContentSelector() {
		return contentSelector;
	}

	public String getTagSelector() {
		return tagSelector;
	}

	public String getFoldername() {
		return foldername;
	}

	public String getOutputfile() {
		return outputfile;
	}
}

package siarhei.pashkou.usecases.codecastsummary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CodecastSummaryResponseModel {
	public String title;
	public LocalDate publishedDate;
	public boolean isViewable = false;
	public boolean isDownloadable = false;
	public String permalink;
	
	private List<CodecastSummary> codeCastSummaries;
	
	public CodecastSummaryResponseModel(){
		codeCastSummaries = new ArrayList<>();
	}
	
	public List<CodecastSummary> getCodecastSummaries() {
		return codeCastSummaries;
	}
	
	public void addCodecastSummary(CodecastSummary codecastSummary){
		codeCastSummaries.add(codecastSummary);
	}
}

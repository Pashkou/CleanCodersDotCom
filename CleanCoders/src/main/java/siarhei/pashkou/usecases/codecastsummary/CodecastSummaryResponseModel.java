package siarhei.pashkou.usecases.codecastsummary;

import java.util.ArrayList;
import java.util.List;

public class CodecastSummaryResponseModel {
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

package siarhei.pashkou.usecases.codecastsummary;

import java.util.ArrayList;
import java.util.List;

import siarhei.pashkou.usecases.CodecastResponseModel;

public class CodecastSummaryResponseModel extends CodecastResponseModel{
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

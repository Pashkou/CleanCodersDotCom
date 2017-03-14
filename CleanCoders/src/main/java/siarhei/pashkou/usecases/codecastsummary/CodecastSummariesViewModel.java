package siarhei.pashkou.usecases.codecastsummary;

import java.util.ArrayList;
import java.util.List;

import siarhei.pashkou.presenter.CodecastSummaryViewModel;

public class CodecastSummariesViewModel {
	public List<CodecastSummaryViewModel> viewModels = new ArrayList<>();

	public void addModel(CodecastSummaryViewModel codecastSummaryViewModel) {
		viewModels.add(codecastSummaryViewModel);		
	}
}

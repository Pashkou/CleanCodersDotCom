package siarhei.pashkou.usecases.codecastsummary;

import java.util.ArrayList;
import java.util.List;

import siarhei.pashkou.presenter.CodecastSummaryViewModel;
import siarhei.pashkou.usecases.CodecastViewModel;

public class CodecastSummariesViewModel extends CodecastViewModel {
	public List<CodecastSummaryViewModel> viewModels = new ArrayList<>();

	public void addModel(CodecastSummaryViewModel codecastSummaryViewModel) {
		viewModels.add(codecastSummaryViewModel);		
	}
}

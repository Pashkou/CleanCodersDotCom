package siarhei.pashkou.usecases.codecastsummary;

import siarhei.pashkou.model.User;

public interface CodecastSummaryInputBoundary {
	 void summarizeCodecasts(User user, CodecastSummaryOutputBoundary presenter);
}

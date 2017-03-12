package siarhei.pashkou.presenter;

import java.time.format.DateTimeFormatter;

import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.License.LicenseType;
import siarhei.pashkou.model.User;
import siarhei.pashkou.usecases.CodecastUseCase;
import siarhei.pashkou.usecases.codecastsummary.PresentableCodecast;

public class CodecastPresenter {
	public PresentableCodecast doFormat(User logedInUser, Codecast codecast) {
		PresentableCodecast pc = new PresentableCodecast();
		pc.isViewable = CodecastUseCase.isLincenseForCodecast(LicenseType.VIEWABLE, logedInUser, codecast);
		pc.isDownloadable = CodecastUseCase.isLincenseForCodecast(LicenseType.DOWNLOADABLE, logedInUser, codecast);
		pc.title = codecast.getTitle();
		pc.permalink = codecast.getPermalink();
		pc.publishedDate = codecast.getPublishedDate().
				format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).
				toString();
		return pc;
	}
}

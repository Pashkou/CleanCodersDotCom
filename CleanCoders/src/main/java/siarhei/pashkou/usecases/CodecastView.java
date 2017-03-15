package siarhei.pashkou.usecases;

public interface CodecastView<V extends CodecastViewModel> {

	String generateView(V responseModel);
	
}

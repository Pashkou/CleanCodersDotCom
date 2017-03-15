package siarhei.pashkou.usecases;

public interface CodecastOutputBoundary<V extends CodecastViewModel, R extends CodecastResponseModel> {

	V getViewModel();

	void present(R codecastResponseModel);

}

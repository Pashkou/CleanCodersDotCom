package siarhei.pashkou.usecases;

public interface CodecastInputBoundary<V extends CodecastViewModel, R extends CodecastResponseModel> {
	 void execute(RequestModel requestModel, CodecastOutputBoundary<V,R> presenter);
}

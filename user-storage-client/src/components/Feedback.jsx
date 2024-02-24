import Loader from "../UI/loader/Loader";

export default function Feedback({isLoading, errorMessage, error, isSuccess, children}) {
    return (
        <div className="container mt-3">
            <div className="row">
                <div className="col-lg-6 mx-auto">
                    <div className="card shadow">
                        <div className="card-body">
                            <h1 className="text-center mb-4">Feedback:</h1>
                            {isLoading && (
                                <div style={{display: 'flex', justifyContent: 'center', marginTop: 30}}>
                                    <Loader/>
                                </div>
                            )}
                            {errorMessage && (
                                <h3 className="text-center">{errorMessage}</h3>
                            )}
                            {error && (
                                <div className="text-center">
                                    <h5>{error.time}</h5>
                                    <h5>{error.message}</h5>
                                    {
                                        error.errorCount &&
                                        <h5>Number of errors found: {error.errorCount}</h5>
                                    }
                                </div>
                            )}
                            {isSuccess && (
                                <h3 className="text-center">{children}</h3>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
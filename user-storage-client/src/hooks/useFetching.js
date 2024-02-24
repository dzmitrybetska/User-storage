import {useState} from "react";

export const useFetching = (callback) => {
    const [isLoading, setIsLoading] = useState(false);
    const [errorResponse, setErrorResponse] = useState();
    const [errorMessage, setErrorMessage] = useState('');
    const [isSuccess, setIsSuccess] = useState(false);

    const fetching = async (...args) => {
        try {
            setIsLoading(true);
            await callback(...args);
            setIsSuccess(true);
            setErrorMessage('');
            setErrorResponse(null);
        } catch (error) {
            if (error.response && error.response.status === 400) {
                const validationError = error.response.data;
                setErrorResponse(validationError);
                setErrorMessage(error.message);
            } else {
                setErrorMessage(error.message);
            }
            setIsSuccess(false);
        } finally {
            setIsLoading(false);
        }
    };

    return [fetching, isLoading, errorMessage, isSuccess, errorResponse];
}
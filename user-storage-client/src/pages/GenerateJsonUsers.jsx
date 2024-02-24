import {useFetching} from "../hooks/useFetching";
import UserService from "../api/UserService";
import MyButton from "../UI/button/MyButton";
import Feedback from "../components/Feedback";

export default function GenerateJsonUsers() {

    const [fetchUsers, isLoading, errorMessage, isSuccess, errorResponse] = useFetching(downloadJsonFile);

    function handleOnClick() {
        fetchUsers();
    }

    async function downloadJsonFile() {
        const response = await UserService.getFile(50000);
        const userRequests = response.data;

        const jsonData = JSON.stringify(userRequests);
        const blob = new Blob([jsonData], {type: 'application/json'});

        const fileUrl = window.URL.createObjectURL(blob);

        const downloadLink = document.createElement('a');
        downloadLink.href = fileUrl;
        downloadLink.setAttribute('download', 'users.json');
        downloadLink.style.display = 'none';

        document.body.appendChild(downloadLink);
        downloadLink.click();
        document.body.removeChild(downloadLink);

        window.URL.revokeObjectURL(fileUrl);
    }

    return (
        <div className="row">
            <div className="col-12 d-flex flex-column align-items-center">
                <Feedback
                    isLoading={isLoading}
                    errorMessage={errorMessage}
                    isSuccess={isSuccess}
                    error={errorResponse}
                >
                    The file has been generated
                </Feedback>
                <MyButton
                    type="button"
                    onClick={handleOnClick}
                    className="btn btn-success mt-5"
                >
                    Generate a file with 50000 users
                </MyButton>
            </div>
        </div>
    );
}
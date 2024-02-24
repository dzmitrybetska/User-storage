import {useState} from "react";
import AddingUsers from "../components/AddingUsers";
import UserService from "../api/UserService";
import {useFetching} from "../hooks/useFetching";
import Feedback from "../components/Feedback";

export default function AddUsers() {

    const [selectedFile, setSelectedFile] = useState(null);
    const [fetchUsers, isLoading, errorMessage, isSuccess, errorResponse] = useFetching(handleUploadFile);

    function handleSubmit(event) {
        event.preventDefault();
        if (selectedFile) {
            fetchUsers(selectedFile);
            handleDelete();
        }
    }

    async function handleUploadFile(file) {
        await UserService.addAll(file);
    }

    function handleFileChange(event) {
        setSelectedFile(event.target.files[0]);
    }

    function handleDelete() {
        const fileInput = document.getElementById('formFile');
        fileInput.value = '';
        if (selectedFile) {
            setSelectedFile(null);
        }
    }

    return (
        <div>
            <Feedback
                isLoading={isLoading}
                errorMessage={errorMessage}
                error={errorResponse}
                isSuccess={isSuccess}
            >
                Users added successfully
            </Feedback>
            <AddingUsers
                handleSubmit={handleSubmit}
                handleFileChange={handleFileChange}
                handleDelete={handleDelete}
            />
        </div>
    );
}
import MyButton from "../UI/button/MyButton";
import MyInput from "../UI/input/MyInput";

export default function AddingUsers({handleSubmit, handleFileChange, handleDelete}) {
    return (
        <div className="container mt-5">
            <form onSubmit={handleSubmit} className="d-flex justify-content-center align-items-center">
                <div className="col-sm-4 mt-2 mb-2">
                    <MyInput
                        accept=".json"
                        className="form-control"
                        type="file" id="formFile"
                        onChange={handleFileChange}/>
                </div>
                <MyButton type="submit" className="btn btn-outline-success m-lg-1">Send</MyButton>
                <MyButton type="button" onClick={handleDelete} className="btn btn-outline-danger ml-2">Delete</MyButton>
            </form>
        </div>
    );
}
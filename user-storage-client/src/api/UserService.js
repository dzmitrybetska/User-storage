import axios from "axios";

export default class UserService {
    static async getAll(page, size) {
        return await axios.get('http://localhost:8080/api/v1/getUsers',
            {
                params: {
                    page: page,
                    size: size
                }
            });
    }

    static async addAll(file) {
        const formData = new FormData();
        formData.append('file', file);
        await axios.post('http://localhost:8080/api/v1/uploadJson', formData);
    }

    static async getFile(quantity) {
        return await axios.get(`http://localhost:8080/api/v1/downloadUsersJson/${quantity}`);
    }
}
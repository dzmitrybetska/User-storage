import {useEffect, useState} from "react";
import {useUsers} from "../hooks/useUsers";
import Loader from "../UI/loader/Loader";
import UserFilter from "../components/UserFilter";
import UsersView from "../components/UsersView";
import {useFetching} from "../hooks/useFetching";
import UserService from "../api/UserService";
import {getPageCount} from "../utils/Pages";
import Pagination from "../UI/pagination/Pagination";

export default function Users() {
    const [users, setUsers] = useState([]);
    const [filter, setFilter] = useState({sort: "", query: ""});
    const sortedAndSearchedUsers = useUsers(users, filter.sort, filter.query);
    const [totalPages, setTotalPages] = useState(0);
    const [size, setSize] = useState(50);
    const [page, setPage] = useState(0);

    const [fetchUsers, isLoading, errorMessage] = useFetching(async (page, size) => {
        const response = await UserService.getAll(page, size);
        const usersPage = response.data;
        setUsers(usersPage.content);
        const totalCount = usersPage.totalElements;
        setTotalPages(getPageCount(totalCount, size));
    });

    useEffect(() => {
        fetchUsers(page, size);
    }, []);

    const changePage = (page) => {
        setPage(page);
        fetchUsers(page, size);
    }

    return (
        <section>
            <UserFilter filter={filter} setFilter={setFilter}/>
            {errorMessage &&
                <h1>An error has occurred: ${errorMessage}</h1>}
            {isLoading
                ?
                <div style={{display: 'flex', justifyContent: 'center', marginTop: 50}}><Loader/></div>
                :
                <UsersView users={sortedAndSearchedUsers}/>
            }
            <Pagination
                currentPage={page}
                changePage={changePage}
                totalPages={totalPages}
            />
        </section>
    );
}
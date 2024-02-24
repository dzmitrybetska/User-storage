import UserItem from "./UserItem";

export default function UsersView({users}) {

    if (!users.length) {
        return (
            <h3>No users found</h3>
        );
    }

    return (
        <table className="table table-bordered table-hover shadow">
            <thead>
            <tr className="text-center">
                <th>id</th>
                <th>name</th>
                <th>surname</th>
                <th>login</th>
            </tr>
            </thead>
            <tbody className="text-center">
            {users.map(user =>
                <UserItem key={user.id} user={user}/>)}
            </tbody>
        </table>
    );
}
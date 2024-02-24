export default function UserItem({user}) {
    return (
        <tr key={user.id}>
            <td>{user.id}</td>
            <td>{user.name}</td>
            <td>{user.surname}</td>
            <td>{user.login}</td>
        </tr>
    );
}
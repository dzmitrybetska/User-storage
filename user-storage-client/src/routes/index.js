import Home from "../pages/Home";
import AddUsers from "../pages/AddUsers";
import Users from "../pages/Users";
import GenerateJsonUsers from "../pages/GenerateJsonUsers";

export const routes = [
    {exact: true, path: "/", element: <Home/>},
    {exact: true, path: "/view-users", element: <Users/>},
    {exact: true, path: "/add-users", element: <AddUsers/>},
    {exact: true, path: "/generate-file", element: <GenerateJsonUsers/>}
];
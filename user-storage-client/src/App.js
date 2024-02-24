import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import "/node_modules/bootstrap/dist/js/bootstrap.min.js"
import {BrowserRouter} from "react-router-dom";
import NavBar from "./navbar/NavBar";
import AppRouter from "./components/AppRouter";

function App() {
    return (
        <BrowserRouter>
            <NavBar/>
            <AppRouter/>
        </BrowserRouter>
    );
}

export default App;

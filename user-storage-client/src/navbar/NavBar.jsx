import {Link} from "react-router-dom";

export default function NavBar() {
    return (
        <nav className="navbar navbar-expand-lg">
            <div className="container-fluid">
                <Link className="navbar-brand text-black" to={"/"} style={{fontSize: "22px"}}>User-storage</Link>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <Link className="nav-link active text-black"
                                  aria-current="page"
                                  to={"/view-users"}
                                  style={{fontSize: "22px"}}
                            >
                                View All Users
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link active text-black"
                                  aria-current="page"
                                  to={"/add-users"}
                                  style={{fontSize: "22px"}}
                            >
                                Add Users
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link active text-black"
                                  aria-current="page"
                                  to={"/generate-file"}
                                  style={{fontSize: "22px"}}
                            >
                                Generate file
                            </Link>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
}
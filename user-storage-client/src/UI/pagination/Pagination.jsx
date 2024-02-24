export default function Pagination({totalPages, currentPage, changePage}) {
    const visiblePages = 20;
    const halfVisiblePages = Math.floor(visiblePages / 2);

    let startPage = Math.max(currentPage - halfVisiblePages, 0);
    let endPage = Math.min(startPage + visiblePages - 1, totalPages - 1);
    startPage = Math.max(endPage - visiblePages + 1, 0);

    const handlePageClick = (page) => {
        if (page !== currentPage) {
            changePage(page);
        }
    }

    return (
        <nav>
            <ul className="pagination ms-2">
                <li className={currentPage === 0 ? 'page-item disabled' : 'page-item'}>
                    <a
                        onClick={() => handlePageClick(currentPage - 1)}
                        className="page-link btn"
                        href="#"
                        aria-label="Previous"
                    >
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                {Array.from({length: endPage - startPage + 1}).map((_, index) => {
                    const page = startPage + index;
                    return (
                        <li
                            className={page === currentPage ? 'page-item active' : 'page-item'}
                            onClick={() => handlePageClick(page)}
                            key={page}
                        >
                            <a className="page-link link-dark btn" href="#">
                                {page + 1}
                            </a>
                        </li>
                    )
                })}
                <li className={currentPage === totalPages - 1 ? 'page-item disabled' : 'page-item'}>
                    <a
                        onClick={() => handlePageClick(currentPage + 1)}
                        className="page-link btn"
                        href="#"
                        aria-label="Next"
                    >
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    )
}
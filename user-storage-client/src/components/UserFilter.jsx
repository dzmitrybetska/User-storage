import MySelect from "../UI/select/MySelect";
import MyInput from "../UI/input/MyInput";

export default function UserFilter({filter, setFilter}) {
    return (
        <div>
            <div className="row">
                <div className="col-sm-5 mt-2 mb-2 ms-2">
                    <MyInput
                        placeholder="Search..."
                        value={filter.query}
                        onChange={(e) => setFilter({...filter, query: e.target.value})
                        }/>
                </div>
                <div className="col-sm-1 mt-2 mb-2">
                    <MySelect
                        value={filter.sort}
                        onChange={selectedSort => setFilter({...filter, sort: selectedSort})}
                        defaultValue="Sort by:"
                        options={[
                            {value: 'name', name: 'Name'},
                            {value: 'surname', name: 'Surname'},
                            {value: 'login', name: 'Login'}
                        ]}
                    />
                </div>
            </div>
        </div>
    );
}
import {useMemo} from "react";

export const useSortedUsers = (users, sort) => {
    return useMemo(() => {
            if (sort) {
                return [...users].sort((a, b) => a[sort].localeCompare(b[sort]));
            }
            return users;
        }, [users, sort]
    );
}

export const useUsers = (users, sort, query) => {
    const sortedUsers = useSortedUsers(users, sort);
    const queryInLowerCase = query.toLowerCase();
    return useMemo(() => {
        return sortedUsers.filter((user) => user.name.toLowerCase().includes(queryInLowerCase)
            || user.surname.toLowerCase().includes(queryInLowerCase)
            || user.login.toLowerCase().includes(queryInLowerCase))
    }, [sortedUsers, queryInLowerCase]);
}
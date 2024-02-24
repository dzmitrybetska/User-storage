export const getPageCount = (totalCount, size) => {
    return Math.ceil(totalCount / size);
};
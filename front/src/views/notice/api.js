import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getNoticeOne = (params) => {
    return getRequest('/notice/getOne', params)
}
export const getNoticeList = (params) => {
    return getRequest('/notice/getByPage', params)
}
export const getNoticeCount = (params) => {
    return getRequest('/notice/count', params)
}
export const addNotice = (params) => {
    return postRequest('/notice/insert', params)
}
export const editNotice = (params) => {
    return postRequest('/notice/update', params)
}
export const addOrEditNotice = (params) => {
    return postRequest('/notice/insertOrUpdate', params)
}
export const deleteNotice = (params) => {
    return postRequest('/notice/delByIds', params)
}
export const changeOpenNotice = (params) => {
    return postRequest('/notice/changeOpenNotice', params)
}
export const changeCloseNotice = (params) => {
    return postRequest('/notice/changeCloseNotice', params)
}
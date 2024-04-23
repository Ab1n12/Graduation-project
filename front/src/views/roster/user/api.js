import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getUserList = (params) => {
    return getRequest('/user/getUserList', params)
}


export const getUserAll = (params) => {
    return getRequest('/user/getUserAll', params)
}
export const enableUser = (params) => {
    return postRequest('/user/enable', params)
}
export const disableUser = (params) => {
    return postRequest('/user/disable', params)
}
export const deleteUser = (params) => {
    return postRequest('/user/delByIds', params)
}
export const importUserData = (params) => {
    return postBodyRequest('/user/importData', params)
}
export const resetUserPass = (params) => {
    return postRequest('/user/resetPass', params)
}

export const getAllRoleList = (params) => {
    return getRequest('/role/getAllList', params)
}
export const addUser = (params) => {
    return postRequest('/user/admin/add', params)
}
export const editUser = (params) => {
    return postRequest('/user/admin/edit', params)
}

export const getTea = (params) => {
    return postRequest('/user/getTea', params)
}
export const getAdmin = (params) => {
    return postRequest('/user/getAdmin', params)
}
export const getStu = (params) => {
    return postRequest('/user/getStu', params)
}
export const getAll = (params) => {
    return getRequest('/user/getAll', params)
}

import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getTopicOne = (params) => {
    return getRequest('/topic/getOne', params)
}
export const getTopicList = (params) => {
    return getRequest('/topic/getByPage', params)
}
export const getTopicCount = (params) => {
    return getRequest('/topic/count', params)
}
export const addTopic = (params) => {
    return postRequest('/topic/insert', params)
}
export const editTopic = (params) => {
    return postRequest('/topic/update', params)
}
export const addOrEditTopic = (params) => {
    return postRequest('/topic/insertOrUpdate', params)
}
export const deleteTopic = (params) => {
    return postRequest('/topic/delByIds', params)
}
export const uploadFile = (params) => {
    return postRequest('/topic/uploadFile', params)
}
export const auditTopic = (params) => {
    return postRequest('/topic/auditTopic', params)
}
export const checkNotTopic = (params) => {
    return postRequest('/topic/checkNotTopic', params)
}

export const checkNotTopicByTea = (params) => {
    return postRequest('/topic/checkNotTopicByTea', params)
}

export const getMyStudent = (params) => {
    return postRequest('/topic/getMyStudent', params)
}

export const getAllUser = (params) => {
    return postRequest('/topic/getAllUser', params)
}
export const getUserByPermission = (params) => {
    return getRequest('/permission/getUserByPermission', params)
}

export const getUserList = (params) => {
    return getRequest('/user/getMyStudent', params)
}

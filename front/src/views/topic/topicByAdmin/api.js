import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getTopicList = (params) => {
    return getRequest('/topic/getByAdminPage', params)
}
export const getTopicCount = (params) => {
    return getRequest('/topic/count', params)
}
export const checkTopic = (params) => {
    return postRequest('/topic/checkTopic', params)
}
export const passTopic = (params) => {
    return postRequest('/topic/passTopic', params)
}
export const notPassTopic = (params) => {
    return postRequest('/topic/notPassTopic', params)
}

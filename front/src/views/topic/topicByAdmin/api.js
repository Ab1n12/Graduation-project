import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getTopicList = (params) => {
    return getRequest('/topic/getByAdminPage', params)
}
export const getTopicCount = (params) => {
    return getRequest('/topic/count', params)
}
// export const addTopic = (params) => {
//     return postRequest('/topic/insert', params)
// }
// export const editTopic = (params) => {
//     return postRequest('/topic/update', params)
// }
// export const addOrEditTopic = (params) => {
//     return postRequest('/topic/insertOrUpdate', params)
// }
// export const deleteTopic = (params) => {
//     return postRequest('/topic/delByIds', params)
// }
// export const uploadFile = (params) => {
//     return postRequest('/topic/uploadFile', params)
// }
// export const auditTopic = (params) => {
//     return postRequest('/topic/auditTopic', params)
// }
// export const checkNotTopic = (params) => {
//     return postRequest('/topic/checkNotTopic', params)
// }
export const checkTopic = (params) => {
    return postRequest('/topic/checkTopic', params)
}
export const passTopic = (params) => {
    return postRequest('/topic/passTopic', params)
}
export const notPassTopic = (params) => {
    return postRequest('/topic/notPassTopic', params)
}
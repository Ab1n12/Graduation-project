<template>
    <div>
        <Card>
            <div slot="title">
                <div class="edit-head">
                    <a @click="close" class="back-title">
                        <Icon type="ios-arrow-back" />返回
                    </a>
                    <div class="head-name">编辑</div>
                    <span></span>
                    <a @click="close" class="window-close">
                        <Icon type="ios-close" size="31" class="ivu-icon-ios-close" />
                    </a>
                </div>
            </div>
            <Form ref="form" :model="form" :label-width="100" :rules="formValidate" label-position="left">
                <FormItem label="公告标题" prop="title">
                    <Input v-model="form.title" clearable style="width:570px" />
                </FormItem>
                <FormItem label="公告内容" prop="remark">
                    <Input v-model="form.remark" type="textarea" :rows="4" show-word-limit maxlength="250" clearable style="width:570px" />
                </FormItem>
                <Form-item class="br">
                    <Button @click="handleSubmit" :loading="submitLoading" type="primary">提交并保存</Button>
                    <Button @click="handleReset">重置</Button>
                    <Button type="dashed" @click="close">关闭</Button>
                </Form-item>
            </Form>
        </Card>
    </div>
    </template>
    
    <script>
    import {
        editNotice
    } from "./api";
    export default {
        name: "edit",
        components: {},
        props: {
            data: Object
        },
        data() {
            return {
                submitLoading: false, // 表单提交状态
                form: { // 添加或编辑表单对象初始化数据
                    title: "",
                    dutyId: "",
                    dutyName: "",
                    remark: "",
                },
                // 表单验证规则
                formValidate: {}
            };
        },
        methods: {
            init() {
                this.handleReset();
                this.form = this.data;
            },
            handleReset() {
                this.$refs.form.resetFields();
            },
            handleSubmit() {
                this.$refs.form.validate(valid => {
                    if (valid) {
                        editNotice(this.form).then(res => {
                            this.submitLoading = false;
                            if (res.success) {
                                this.$Message.success("操作成功");
                                this.submited();
                            }
                        });
                    }
                });
            },
            close() {
                this.$emit("close", true);
            },
            submited() {
                this.$emit("submited", true);
            }
        },
        mounted() {
            this.init();
        }
    };
    </script>
    
    <style lang="less">
    // 建议引入通用样式 具体路径自行修改 可删除下面样式代码
    // @import "../../../styles/single-common.less";
    .edit-head {
        display: flex;
        align-items: center;
        justify-content: space-between;
        position: relative;
    
        .back-title {
            color: #515a6e;
            display: flex;
            align-items: center;
        }
    
        .head-name {
            display: inline-block;
            height: 20px;
            line-height: 20px;
            font-size: 16px;
            color: #17233d;
            font-weight: 500;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    
        .window-close {
            z-index: 1;
            font-size: 12px;
            position: absolute;
            right: 0px;
            top: -5px;
            overflow: hidden;
            cursor: pointer;
    
            .ivu-icon-ios-close {
                color: #999;
                transition: color .2s ease;
            }
        }
    
        .window-close .ivu-icon-ios-close:hover {
            color: #444;
        }
    }
    </style>
    
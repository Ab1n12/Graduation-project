<template>
<div>
    <Card>
        <div slot="title">
            <div class="edit-head">
                <a @click="close" class="back-title">
                    <Icon type="ios-arrow-back" />返回
                </a>
                <div class="head-name">添加</div>
                <span></span>
                <a @click="close" class="window-close">
                    <Icon type="ios-close" size="31" class="ivu-icon-ios-close" />
                </a>
            </div>
        </div>
        <Form ref="form" :model="form" :label-width="100" :rules="formValidate" label-position="left">
            <FormItem label="课题名称" prop="title">
                <Input v-model="form.title" clearable style="width:570px" />
            </FormItem>
            <FormItem label="课题类型" prop="type">
                <Select v-model="form.type" clearable style="width:570px">
                    <Option value="毕业设计">毕业设计</Option>
                    <Option value="毕业论文">毕业论文</Option>
                </Select>
            </FormItem>
            <FormItem label="课题难度" prop="level">
                <Select v-model="form.level" clearable style="width:570px">
                    <Option value="简单">简单</Option>
                    <Option value="一般">一般</Option>
                    <Option value="复杂">复杂</Option>
                </Select>
            </FormItem>
            
            <FormItem label="剩余名额" prop="quota">
                <Input v-model="form.quota" type="textarea" :rows="1" clearable style="width:570px" />
            </FormItem>

            <!-- <FormItem label="剩余名额" prop="quota">
                <el-input-number v-model="form.quota" controls-position="right" :min="1" :max="10"></el-input-number>
            </FormItem> -->

            <FormItem label="选题要求" prop="remark">
                <Input v-model="form.remark" type="textarea" :rows="4" show-word-limit maxlength="250" clearable style="width:570px" />
            </FormItem>

            <FormItem label="任务书" prop="taskFile">
                <upload-pic-input @on-change="changeTaskFile" v-model="tempFile" style="width:570px"></upload-pic-input>
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
    addTopic
} from "./api.js";
import uploadPicInput from "@/views/template/file-upload";
export default {
    name: "add",
    components: {
        uploadPicInput,
    },
    data() {
        return {
          tempFile: {},
            submitLoading: false, // 表单提交状态
            form: { // 添加或编辑表单对象初始化数据
                title: "",
                type: "",
                level: "",
                quota: "", 
                remark: "",
                taskFile: "",
            },
            // 表单验证规则
            formValidate: {}
        };
    },
    methods: {
        init() {},
        changeTaskFile(e) {
          this.form.taskFile = e.url;
        },
        handleReset() {
            this.$refs.form.resetFields();
        },
        handleSubmit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    addTopic(this.form).then(res => {
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

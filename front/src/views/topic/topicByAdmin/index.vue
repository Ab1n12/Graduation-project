<template>
    <div class="search">
        <Card v-show="currView=='index'">
            <Row v-show="openSearch" @keydown.enter.native="handleSearch">
                <Form ref="searchForm" :model="searchForm" inline :label-width="0">
                    <Form-item ref="searchForm" :model="searchForm" inline :label-width="0" style="display:flex;">
                        <Form-item label="" prop="title">
                            <Input type="text" v-model="searchForm.title" placeholder="请输入课题名称" clearable style="width: 200px" />
                        </Form-item>
                        <Form-item label="" prop="type">
                            <Select v-model="searchForm.type" placeholder="课题类型" clearable style="width: 200px">
                                <Option value="毕业设计">毕业设计</Option>
                                <Option value="毕业论文">毕业论文</Option>
                            </Select>
                        </Form-item>
                        <!-- <Form-item label="" prop="level">
                            <Select v-model="searchForm.level" placeholder="难度" clearable style="width: 200px">
                                <Option value="简单">简单</Option>
                                <Option value="一般">一般</Option>
                                <Option value="复杂">复杂</Option>
                            </Select>
                        </Form-item> -->
                        <Form-item label="" prop="pass">
                            <Select v-model="searchForm.pass" placeholder="是否被管理员审核" clearable style="width: 200px">
                                <Option :value="1">已审核</Option>
                                <Option :value="0">未审核</Option>
                            </Select>
                        </Form-item>
                        <Form-item style="margin-left:10px;" class="br">
                            <Button @click="handleSearch" type="primary" icon="ios-search" size="small" ghost>搜索</Button>
                            <Button @click="handleReset" type="warning" size="small" icon="md-refresh" ghost>重置</Button>
                        </Form-item>
                        <Form-item style="position:fixed;right:50px;top:130px">
                            <Button type="info" @click="showFilterPanelFlag = !showFilterPanelFlag" class="showFilterPanelFlag" icon="md-settings" size="small" ghost>
                                列筛选</Button>
                            <Button type="warning" @click="modal1 = true" class="showFilterPanelFlag" icon="ios-help-circle-outline" size="small" ghost>
                                使用教程</Button>
                            <Modal v-model="modal1" title="使用教程">
                                <p>1.XXXXXXXXXXXXXXXXXXXXXXXX</p>
                                <p>2.XXXXXXXXXXXXXXXXXXXXXXXX</p>
                                <p>3.XXXXXXXXXXXXXXXXXXXXXXXX</p>
                            </Modal>
                        </Form-item>
                    </Form-item>
                </Form>
            </Row>
            <Row class="operation" style="position:relative;">
                <transition>
                    <div v-show="showFilterPanelFlag" class="filter-panel">
                        <CheckboxGroup v-model="selected">
                            <div v-for="item in mycolumns" :key="item.key">
                                <Checkbox :label="item.title" style="margin: 2px 5px"></Checkbox>
                            </div>
                        </CheckboxGroup>
                    </div>
                </transition>
            </Row>
            <Row v-show="openTip"> </Row>
            <Row>
                <Table :loading="loading" :height="tableHeight" border stripe size="small" :columns="columns" :data="data" ref="table" sortable="custom" @on-sort-change="changeSort" @on-selection-change="changeSelect" @on-row-click="rowClick" :row-class-name="rowClassNmae"></Table>
            </Row>
            <Row type="flex" justify="end" class="page">
                <Page :current="searchForm.pageNumber" :total="total" :page-size="searchForm.pageSize" @on-change="changePage" @on-page-size-change="changePageSize" :page-size-opts="[15,20,50]" size="small" show-total show-elevator show-sizer></Page>
            </Row>
        </Card>
    </div>
    </template>
    
    <script>
    import {
        getTopicList,
        passTopic,
        notPassTopic
    } from "./api.js";
    import uploadPicInput from "@/views/template/file-upload";

    export default {
        name: "single-window",
        components: {
            uploadPicInput
        },
        data() {
            return {
                searchForm: null,
                tableHeight: 0,
                selected: [
                    "选择",
                    "序号",
                    "课题名称",
                    "课题类型",
                    "课题难度",
                    "剩余名额",
                    "选题要求",
                    "发布教师",
                    "创建时间",
                    "创建者",
                    "是否被管理员审核",
                    "修改时间",
                    "修改者",
                    "操作",
                ],
                modal1: false,
                openSearch: true, // 显示搜索
                openTip: true, // 显示提示
                formData: {},
                currView: "index",
                loading: true, // 表单加载状态
                searchForm: { // 搜索框初始化对象
                    pageNumber: 1, // 当前页数
                    pageSize: 15, // 页面大小
                    sort: "createTime", // 默认排序字段
                    order: "desc", // 默认排序方式
                },
                selectList: [], // 多选数据
                selectCount: 0, // 多选计数
                selectRow: 0,
                columns: [
                    // 表头
                    {
                        type: "selection",
                        width: 60,
                        title: "选择",
                        align: "center",
                        fixed: "left",
                    },
                    {
                        title: "序号",
                        width: 85,
                        align: "center",
                        fixed: "left",
                        sortType: true,
                        render: (h, params) => {
                            return h(
                                "span",
                                params.index +
                                (this.searchForm.pageNumber - 1) * this.searchForm.pageSize +
                                1
                            );
                        },
                    },
                    {
                        title: "课题名称",
                        key: "title",
                        minWidth: 200,
                        tooltip: true,
                        align: "center",
                        sortable: false,
                    },
                    {
                        title: "课题类型",
                        key: "type",
                        minWidth: 120,
                        tooltip: true,
                        align: "center",
                        sortable: true,
                    },
                    {
                        title: "课题难度",
                        key: "level",
                        minWidth: 120,
                        tooltip: true,
                        align: "center",
                        sortable: true,
                    },
                    {
                        title: "剩余名额",
                        key: "quota",
                        minWidth: 110,
                        tooltip: true,
                        align: "center",
                        sortable: true,
                    },
                    {
                        title: "选题要求",
                        key: "remark",
                        minWidth: 160,
                        tooltip: true,
                        align: "center",
                        sortable: false,
                    },
                    {
                        title: "发布教师",
                        key: "teaName",
                        minWidth: 120,
                        tooltip: true,
                        align: "center",
                        sortable: false,
                    },
                    {
                        title: "创建时间",
                        key: "createTime",
                        sortable: true,
                        sortType: "desc",
                        minWidth: 180,
                        align: "center",
                        tooltip: true,
                    },
                    {
                        title: "创建者",
                        key: "createBy",
                        sortable: true,
                        sortType: "desc",
                        minWidth: 100,
                        align: "center",
                        tooltip: true,
                    },
                    {
                        title: "是否被管理员审核",
                        key: "pass",
                        minWidth: 135,
                        align: "center",
                        tooltip: true,
                        sortable: false,
                        render: (h, params) => {
                            if (params.row.pass) {
                                return h("div", [
                                    h(
                                        "span", {
                                            style: {
                                                color: "#3CB371",
                                            },
                                        },
                                        "已审核"
                                    ),
                                ]);
                            } else {
                                return h("div", [
                                    h(
                                        "span", {
                                            style: {
                                                color: "#ff0000",
                                            },
                                        },
                                        "未审核"
                                    ),
                                ]);
                            }
                        },
                    },
                    {
                        title: "修改时间",
                        key: "updateTime",
                        minWidth: 180,
                        align: "center",
                        tooltip: true,
                    },
                    {
                        title: "修改者",
                        key: "updateBy",
                        minWidth: 100,
                        align: "center",
                        tooltip: true,
                    },
                    {
                        title: "操作",
                        key: "action",
                        align: "center",
                        fixed: "right",
                        width: 200,
                        render: (h, params) => {
                            return h("div", [
                            h(
                                "Button", {
                                    props: {
                                        type: "primary",
                                        size: "small",
                                        icon: "ios-create-outline",
                                        ghost: true,
                                        disabled: params.row.pass
                                    },
                                    style: {
                                        marginRight: "5px"
                                    },
                                    on: {
                                        click: () => {
                                            this.passTopicFx(params.row);
                                        }
                                    }
                                },
                                "审核"
                            ),
                            h(
                                "Button", {
                                    props: {
                                        type: "error",
                                        size: "small",
                                        icon: "ios-create-outline",
                                        ghost: true,
                                        disabled: !params.row.pass
                                    },
                                    style: {
                                        marginRight: "5px",
                                    },
                                    on: {
                                        click: () => {
                                            this.notPassTopicFx(params.row);
                                        }
                                    }
                                },
                                "驳回"
                            )
                            ]);
                        }
                    }
                ],
                data: [], // 表单数据
                pageNumber: 1, // 当前页数
                pageSize: 10, // 页面大小
                total: 0, // 表单数据总数
                showFilterPanelFlag: false,
                mySelectRow: {}
            };
        },
        methods: {
            init() {
                this.getDataList();
            },
            passTopicFx(e) {
                var that = this;
                passTopic({
                    id: e.id
                }).then(res => {
                    if (res.success) {
                        this.$Message.success("课题审核成功");
                        that.getDataList();
                    }
                })
            },
            notPassTopicFx(e) {
                var that = this;
                notPassTopic({
                  id: e.id
                }).then(res => {
                    if (res.success) {
                        this.$Message.success("课题驳回成功");
                        that.getDataList();
                    }
                })
            },
            submited() {
                this.currView = "index";
                this.getDataList();
            },
            changePage(v) {
                this.searchForm.pageNumber = v;
                this.getDataList();
                this.clearSelectAll();
            },
            changePageSize(v) {
                this.searchForm.pageSize = v;
                this.getDataList();
            },
            rowClick(row, index) {
                this.selectRow = row;
            },
            rowClassNmae(row, index) {
                if (row.id == this.selectRow.id) {
                    return "rowClassNmaeColor";
                }
                return "";
            },

            handleSearch() {
                this.searchForm.pageNumber = 1;
                this.searchForm.pageSize = 15;
                this.getDataList();
            },
            handleReset() {
                this.$refs.searchForm.resetFields();
                this.searchForm.pageNumber = 1;
                this.searchForm.pageSize = 15;
                // 重新加载数据
                this.getDataList();
            },
            changeSort(e) {
                this.searchForm.sort = e.key;
                this.searchForm.order = e.order;
                if (e.order === "normal") {
                    this.searchForm.order = "";
                }
                this.getDataList();
            },
            clearSelectAll() {
                this.$refs.table.selectAll(false);
            },
            changeSelect(e) {
                this.selectList = e;
                this.selectCount = e.length;
            },
            getDataList() {
                this.loading = true;
                getTopicList(this.searchForm).then(res => {
                    this.loading = false;
                    if (res.success) {
                        this.data = res.result.records;
                        this.total = res.result.total;
                    }
                });
            }
        },
        mounted() {
            this.init();
            this.tableHeight = Number(window.innerHeight - 273);
            this.mycolumns = this.columns;
            let showcolumns = [];
            for (var i = 0; i < this.selected.length; i++) {
                var item = this.selected[i];
                for (var j = 0; j < this.columns.length; j++) {
                    if (this.columns[j].title == item) {
                        showcolumns.push(this.columns[j]);
                    }
                }
            }
            this.columns = showcolumns;
        },
        watch: {
            selected: function (newcolumns) {
                let showcolumns = [];
                for (var i = 0; i < this.mycolumns.length; i++) {
                    var item = this.mycolumns[i];
                    if (item.title == undefined) showcolumns.push(item);
                    else if (newcolumns.contains(item.title)) showcolumns.push(item);
                }
                this.columns = showcolumns;
            },
        },
    };
    </script>
    
    <style lang="less">
    // @import "../../../styles/table-common.less";
    .search {
        .operation {
            margin-bottom: 2vh;
        }
    
        .select-count {
            font-weight: 600;
            color: #40a9ff;
        }
    
        .select-clear {
            margin-left: 10px;
        }
    
        .page {
            margin-top: 2vh;
        }
    
        .drop-down {
            margin-left: 5px;
        }
    }
    
    .filter-panel {
        width: 166px;
        min-height: 120px;
        height: 200px;
        position: absolute;
        background-color: white;
        z-index: 9999;
        margin-left: 1px;
        overflow-y: scroll;
        border: 1px solid blue;
        top: 35px;
        right: 10px;
    }
    
    .openSearch {
        position: absolute;
        right: 240px;
    }
    
    .openTip {
        position: absolute;
        right: 130px;
    }
    
    .showFilterPanelFlag {
        position: static !important;
        right: 10px;
        margin-right: 10px;
    }
    
    .ivu-table td {
        height: 38px !important;
    }
    
    .ivu-table-cell-with-expand {
        height: 38px !important;
        line-height: 38px !important;
    }
    
    .ivu-table .rowClassNmaeColor td {
        background-color: #b0b3b6 !important;
        color: #ffffff !important;
        font-size: 12px;
    }
    </style>
    
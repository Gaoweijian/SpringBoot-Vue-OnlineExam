//查询所有题库
<template>
  <div class="exam">
    <!--增加查询选项-->
    <el-row :gutter="20">
      <el-col :span="6">
        <div class="grid-content">
          <label class="label-size">试卷名称：</label>
          <el-input class="w150" v-model="queryParams.subject" placeholder="请输入内容"></el-input>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content">
          <label class="label-size">题目类型：</label>
          <el-select v-model="queryParams.type" placeholder="请选择">
            <el-option
              v-for="item in types"
              :label="item.name"
              :key="item.value"
              :value="item.value">
            </el-option>
          </el-select>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content">
          题目信息：
          <el-input class="w150" v-model="queryParams.question" placeholder="请输入内容"></el-input>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content">
          <el-button type="primary" @click="getAnswerInfo()">查询</el-button>
        </div>
      </el-col>
    </el-row>
    <el-table :data="pagination.records" border :row-class-name="tableRowClassName">
      <el-table-column fixed="left" prop="subject" label="试卷名称" width="180"></el-table-column>
      <el-table-column prop="question" label="题目信息" width="490"></el-table-column>
      <el-table-column prop="section" label="所属章节" width="200"></el-table-column>
      <el-table-column prop="type" label="题目类型" width="200"></el-table-column>
      <el-table-column prop="score" label="试题分数" width="150"></el-table-column>
      <el-table-column prop="level" label="难度等级" width="133"></el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.current"
      :page-sizes="[6, 10]"
      :page-size="pagination.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.total"
      class="page"
    ></el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      types: [
        {
          name: '全选',
          value: ''
        },
        {
          name: '选择题',
          value: '选择题'
        },
        {
          name: '判断题',
          value: '判断题'
        },
        {
          name: '填空题',
          value: '填空题'
        }
      ],
      queryParams: {
        type: '',
        question: '',
        subject: ''
      },
      pagination: {
        //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 6, //每页条数
      }
    };
  },
  created() {
    this.getAnswerInfo();
  },
  methods: {
    getAnswerInfo() {
      var params = {
        currentPage: this.pagination.current,
        size: this.pagination.size, //每页条数
        type: this.queryParams.type,
        question: this.queryParams.question,
        subject: this.queryParams.subject
      }
      //分页查询所有试卷信息
      this.$axios({
        method: 'get',
        url: `/api/answers`,
        params: params
      })
        .then(res => {
          this.pagination = res.data.data;
          console.log(res);
        })
        .catch(error => {
        });
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val;
      this.getAnswerInfo();
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val;
      this.getAnswerInfo();
    },
    tableRowClassName({row, rowIndex}) {
      if (rowIndex % 2 == 0) {
        return "warning-row";
      } else {
        return "success-row";
      }
    }
  }
};
</script>
<style lang="less" scoped>
.exam {
  padding: 0px 40px;

  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .edit {
    margin-left: 20px;
  }

  .el-table tr {
    background-color: #DD5862 !important;
  }
}

.el-table .warning-row {
  background: #000 !important;
}

.el-table .success-row {
  background: #DD5862;
}

</style>

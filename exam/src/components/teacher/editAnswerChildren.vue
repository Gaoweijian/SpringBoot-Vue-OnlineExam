// 添加题库
<template>
  <div class="edit">
    <el-tabs v-model="activeName">
    <!--修改试题-->
    <el-tab-pane name="third">
      <span slot="label"><i class="el-icon-edit"></i>修改试题</span>
      <section class="append">
<!--        <el-row :gutter="20">-->
<!--          <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
<!--          <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
<!--          <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
<!--          <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
<!--        </el-row>-->
        <ul>
          <li>
            <span>题目类型:</span>
            <el-select v-model="optionValue" placeholder="请选择题型" class="w150">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li v-if="optionValue == '选择题'">
            <span>所属章节：</span>
            <el-input
              placeholder="请输入对应章节"
              v-model="postChange.section"
              class="w150"
              clearable>
            </el-input>
          </li>
          <li v-if="optionValue == '填空题'">
            <span>所属章节：</span>
            <el-input
              placeholder="请输入对应章节"
              v-model="postFill.section"
              class="w150"
              clearable>
            </el-input>
          </li>
          <li v-if="optionValue == '判断题'">
            <span>所属章节：</span>
            <el-input
              placeholder="请输入对应章节"
              v-model="postJudge.section"
              class="w150"
              clearable>
            </el-input>
          </li>
          <li v-if="optionValue == '多项选择题'">
            <span>所属章节：</span>
            <el-input
              placeholder="请输入对应章节"
              v-model="postMultiChange.section"
              class="w150"
              clearable>
            </el-input>
          </li>
          <li v-if="optionValue == '选择题'">
            <span>难度等级:</span>
            <el-select v-model="postChange.level" placeholder="选择难度等级" class="w150">
              <el-option
                v-for="item in levels"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li v-if="optionValue == '填空题'">
            <span>难度等级:</span>
            <el-select v-model="postFill.level" placeholder="选择难度等级" class="w150">
              <el-option
                v-for="item in levels"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li v-if="optionValue == '判断题'">
            <span>难度等级:</span>
            <el-select v-model="postJudge.level" placeholder="选择难度等级" class="w150">
              <el-option
                v-for="item in levels"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li v-if="optionValue == '多项选择题'">
            <span><label style="color: red"> *</label>难度等级:</span>
            <el-select v-model="postMultiChange.level" placeholder="选择难度等级" class="w150">
              <el-option
                v-for="item in levels"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li v-if="optionValue == '选择题'">
            <span>正确选项:</span>
            <el-select v-model="postChange.rightAnswer" placeholder="选择正确答案" class="w150">
              <el-option
                v-for="item in rights"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
          <li v-if="optionValue == '多项选择题'">
            <span>正确选项:</span>
            <el-select multiple  v-model="postMultiChange.rightAnswers" placeholder="选择正确答案" class="w300">
              <el-option
                v-for="item in multiRights"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </li>
        </ul>
        <!-- 选择题部分 -->
        <div class="change" v-if="optionValue == '选择题'">
          <div class="title">
            <el-tag>题目:</el-tag><span>在下面的输入框中输入题目,形如--DNS 服务器和DHCP服务器的作用是（）</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postChange.question"
              placeholder="请输入题目内容"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="options">
            <ul>
              <li>
                <el-tag type="success">A</el-tag>
                <el-input
                  placeholder="请输入选项A的内容"
                  v-model="postChange.answerA"
                  clearable="">
                </el-input>
              </li>
              <li>
                <el-tag type="success">B</el-tag>
                <el-input
                  placeholder="请输入选项B的内容"
                  v-model="postChange.answerB"
                  clearable="">
                </el-input>
              </li>
              <li>
                <el-tag type="success">C</el-tag>
                <el-input
                  placeholder="请输入选项C的内容"
                  v-model="postChange.answerC"
                  clearable="">
                </el-input>
              </li>
              <li>
                <el-tag type="success">D</el-tag>
                <el-input
                  placeholder="请输入选项D的内容"
                  v-model="postChange.answerD"
                  clearable="">
                </el-input>
              </li>
            </ul>
          </div>
          <div class="title">
            <el-tag>解析:</el-tag><span>在下面的输入框中输入题目解析</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postChange.analysis"
              placeholder="请输入答案解析"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="submit">
            <el-button type="primary" @click="changeSubmit()">提交修改</el-button>
          </div>
        </div>
        <!-- 填空题部分 -->
        <div class="change fill" v-if="optionValue == '填空题'">
          <div class="title">
            <el-tag>题目:</el-tag><span>输入题目,形如--从计算机网络系统组成的角度看，计算机网络可以分为()和()。注意需要考生答题部分一定要用括号（英文半角）括起来。</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postFill.question"
              placeholder="请输入题目内容"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="fillAnswer">
            <el-tag>正确答案:</el-tag>
            <el-input v-model="postFill.answer"></el-input>
          </div>
          <div class="title analysis">
            <el-tag type="success">解析:</el-tag><span>下方输入框中输入答案解析</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postFill.analysis"
              placeholder="请输入答案解析"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="submit">
            <el-button type="primary" @click="fillSubmit()">提交修改</el-button>
          </div>
        </div>
        <!-- 判断题 -->
        <div class="change judge" v-if="optionValue == '判断题'">
          <div class="title">
            <el-tag>题目:</el-tag><span>在下面的输入框中输入题目</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postJudge.question"
              placeholder="请输入题目内容"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="judgeAnswer">
            <el-radio v-model="postJudge.answer" label="T">正确</el-radio>
            <el-radio v-model="postJudge.answer" label="F">错误</el-radio>
          </div>
          <div class="title">
            <el-tag>解析:</el-tag><span>在下面的输入框中输入题目解析</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postJudge.analysis"
              placeholder="请输入答案解析"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="submit">
            <el-button type="primary" @click="judgeSubmit()">提交修改</el-button>
          </div>
        </div>


        <!-- 选择题部分 -->
        <div class="change" v-if="optionValue == '多项选择题'">
          <div class="title">
            <el-tag>题目:</el-tag><span>在下面的输入框中输入题目,形如--DNS 服务器和DHCP服务器的作用是（）</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postMultiChange.question"
              placeholder="请输入题目内容"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="options">
            <ul>
              <li>
                <el-tag type="success">A</el-tag>
                <el-input
                  placeholder="请输入选项A的内容"
                  v-model="postMultiChange.answerA"
                  clearable="">
                </el-input>
              </li>
              <li>
                <el-tag type="success">B</el-tag>
                <el-input
                  placeholder="请输入选项B的内容"
                  v-model="postMultiChange.answerB"
                  clearable="">
                </el-input>
              </li>
              <li>
                <el-tag type="success">C</el-tag>
                <el-input
                  placeholder="请输入选项C的内容"
                  v-model="postMultiChange.answerC"
                  clearable="">
                </el-input>
              </li>
              <li>
                <el-tag type="success">D</el-tag>
                <el-input
                  placeholder="请输入选项D的内容"
                  v-model="postMultiChange.answerD"
                  clearable="">
                </el-input>
              </li>
              <li>
                <el-tag type="success">E</el-tag>
                <el-input
                  placeholder="请输入选项E的内容"
                  v-model="postMultiChange.answerE"
                  clearable="">
                </el-input>
              </li>
              <li>
                <el-tag type="success">F</el-tag>
                <el-input
                  placeholder="请输入选项F的内容"
                  v-model="postMultiChange.answerF"
                  clearable="">
                </el-input>
              </li>
            </ul>
          </div>
          <div class="title">
            <el-tag>解析:</el-tag><span>在下面的输入框中输入题目解析</span>
            <el-input
              type="textarea"
              rows="4"
              v-model="postMultiChange.analysis"
              placeholder="请输入答案解析"
              resize="none"
              class="answer">
            </el-input>
          </div>
          <div class="submit">
            <el-button type="primary" @click="changeMultiSubmit()">提交修改</el-button>
          </div>
        </div>
      </section>
    </el-tab-pane>
  </el-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeName: 'third',  //活动选项卡
      options: [ //题库类型
        {
          value: '选择题',
          label: '选择题'
        },
        {
          value: '填空题',
          label: '填空题'
        },
        {
          value: '判断题',
          label: '判断题'
        },
        {
          value: '多项选择题',
          label: '多项选择题'
        }
      ],
      difficulty: [ //试题难度
        {
          value: '简单',
          label: '简单'
        },
        {
          value: '一般',
          label: '一般'
        },
        {
          value: '困难',
          label: '困难'
        }
      ],
      difficultyValue: '简单',
      levels: [ //难度等级
        {
          value: '1',
          label: '1'
        },
        {
          value: '2',
          label: '2'
        },
        {
          value: '3',
          label: '3'
        },
        {
          value: '4',
          label: '4'
        },
        {
          value: '5',
          label: '5'
        },
      ],
      rights: [ //正确答案
        {
          value: 'A',
          label: 'A'
        },
        {
          value: 'B',
          label: 'B'
        },
        {
          value: 'C',
          label: 'C'
        },
        {
          value: 'D',
          label: 'D'
        }
      ],
      multiRights:[
        {
          value: 'A',
          label: 'A'
        },
        {
          value: 'B',
          label: 'B'
        },
        {
          value: 'C',
          label: 'C'
        },
        {
          value: 'D',
          label: 'D'
        },
        {
          value: 'E',
          label: 'E'
        },
        {
          value: 'F',
          label: 'F'
        },
      ],
      paperId: null,
      optionValue: '选择题', //题型选中值
      subject: '', //试卷名称用来接收路由参数
      postChange: { //选择题提交内容
        questionId:'',//试题ID
        subject: '', //试卷名称
        level: '', //难度等级选中值
        rightAnswer: '', //正确答案选中值
        section: '', //对应章节
        question: '', //题目
        analysis: '', //解析
        answerA: '',
        answerB: '',
        answerC: '',
        answerD: '',
      },
      postFill: { //填空题提交内容
        questionId:'',//试题ID
        subject: '', //试卷名称
        level: '', //难度等级选中值
        answer: '', //正确答案
        section: '', //对应章节
        question: '', //题目
        analysis: '', //解析
      },
      postJudge: { //判断题提交内容
        questionId:'',//试题ID
        subject: '', //试卷名称
        level: '', //难度等级选中值
        answer: '', //正确答案
        section: '', //对应章节
        question: '', //题目
        analysis: '', //解析
      },
      postMultiChange: { //选择题提交内容
        subject: '', //试卷名称
        level: 1, //难度等级选中值
        rightAnswers: [], //正确答案选中值
        rightAnswer: '', //正确答案选中值
        section: '', //对应章节
        question: '', //题目
        analysis: '', //解析
        answerA: '',
        answerB: '',
        answerC: '',
        answerD: '',
        answerE: '',
        answerF: '',
      },
      postPaper: { //考试管理表对应字段
        paperId: null,
        questionType: null, // 试卷类型 1--选择题  2--填空题   3--判断题
        questionId: null,
      }
    };
  },
  created() {
    this.getParams()
  },
  methods: {
    // handleClick(tab, event) {
    //   console.log(tab, event);
    // },
    create() {
      this.$axios({
        url: '/api/item',
        method: 'post',
        data: {
          changeNumber: this.changeNumber,
          fillNumber: this.fillNumber,
          judgeNumber: this.judgeNumber,
          paperId: this.paperId,
          subject: '计算机网络' //题目数量太少，指定为计算机网络出题
        }
      }).then(res => {
        console.log(res)
        let data = res.data
        if(data.code==200){
          setTimeout(() => {
            this.$router.push({path: '/selectAnswer'})
          },1000)
           this.$message({
            message: data.message,
            type: 'success'
          })
        }else if(data.code==400){
            this.$message({
            message: data.message,
            type: 'error'
          })
        }

      })
    },
    getParams() {
      let subject = this.$route.query.subject //获取试卷名称
      let questionId = this.$route.query.questionId //获取paperId
      let type = this.$route.query.type //type
      console.info(subject, questionId, type);
      //按照类型查询数据
      let API_URL = '';
      if ('选择题' === type) {
        API_URL = '/api/multiQuestionById';
      } else if ('填空题' === type) {
        API_URL = '/api/fillQuestionById';
      } else if ('判断题' === type) {
        API_URL = '/api/judgeQuestionById';
      }else if ('多项选择题' === type) {
        API_URL = '/api/multiQuestionById';
      }
      this.optionValue = type;
      this.$axios({ //提交数据到选择题题库表
        url: API_URL,
        method: 'get',
        params: {questionId:questionId}
      }).then(res => { //添加成功显示提示
        let status = res.data.code
        if(status == 200) {
          if ('选择题' === type) {
            this.postChange = res.data.data;
          } else if ('填空题' === type) {
            this.postFill = res.data.data;
          } else if ('判断题' === type) {
            this.postJudge = res.data.data;
          }else if ('多项选择题' === type) {
            //转换正确选项
            this.postMultiChange =   Object.assign({},res.data.data);
          }
        }
      })
    },
    changeSubmit() { //选择题题库提交
      this.$axios({ //提交数据到选择题题库表
        url: '/api/MultiQuestion',
        method: 'post',
        data: {
          ...this.postChange
        }
      }).then(res => { //添加成功显示提示
        let status = res.data.code
        if(status == 200) {
          this.$message({
            message: '试题修改成功',
            type: 'success'
          })
        }
      })
    },
    fillSubmit() { //填空题提交
      this.$axios({
        url: '/api/fillQuestion',
        method: 'post',
        data: {
          ...this.postFill
        }
      }).then(res => {
        let status = res.data.code
        if(status == 200) {
          this.$message({
            message: '试题修改成功',
            type: 'success'
          })
        }
      })
    },
    judgeSubmit() { //判断题提交
      this.$axios({
        url: '/api/judgeQuestion',
        method: 'post',
        data: {
          ...this.postJudge
        }
      }).then(res => {
        let status = res.data.code
        if(status == 200) {
          this.$message({
            message: '试题修改成功',
            type: 'success'
          })
        }
      })
    },
    changeMultiSubmit() { //选择题题库提交
      //转换多选答案
      // this.postMultiChange.rightAnswer = Object.assign({}, this.postMultiChange.rightAnswers.join(","));
      this.$axios({ //提交数据到选择题题库表
        url: '/api/MultiQuestion',
        method: 'post',
        data: {
          ...this.postMultiChange
        }
      }).then(res => { //添加成功显示提示
        let status = res.data.code
        if(status == 200) {
          this.$message({
            message: '试题修改成功',
            type: 'success'
          })
        }
      })
    },
  },
};
</script>

<style lang="less" scoped>
.edit {
  margin: 0px 40px;
  .box {
    padding: 0px 20px;
    ul li {
      margin: 10px 0px;
      display: flex;
      align-items: center;
      .el-input {
        width: 6%;
      }
      .w150 {
        margin-left: 20px;
        width: 7%;
      }
    }
  }
  .el-icon-circle-plus {
    margin-right: 10px;
  }
  .icon-daoru-tianchong {
    margin-right: 10px;
  }
  .append {
    margin: 0px 20px;
    ul {
      display: flex;
      align-items: center;
      li {
        margin-right: 20px;
      }
    }
    .change {
      margin-top: 20px;
      padding: 20px 16px;
      background-color: #E7F6F6;
      border-radius: 4px;
      .title {
        padding-left: 6px;
        color: #2f4f4f;
        span:nth-child(1) {
          margin-right: 6px;
        }
        .answer {
          margin: 20px 0px 20px 8px;
        }
        .el-textarea {
          width: 98% !important;
        }
      }
      .options {
        ul {
          display: flex;
          flex-direction: column;
        }
        ul li {
          display: flex;
          justify-content: center;
          align-items: center;
          width: 98%;
          margin: 10px 0px;
          span {
            margin-right: 20px;
          }
        }
      }
      .submit {
        display: flex;
        justify-content: center;
        align-items: center;
      }
    }
    .fill {
      .fillAnswer {
        display: flex;
        justify-content: center;
        align-items: center;
        span {
          margin-right: 6px;
        }
        .el-input {
          width: 91% !important;
        }
      }
      .analysis {
        margin-top: 20px;
        margin-left: 5px;
      }
    }
    .judge {
      .judgeAnswer {
        margin-left: 20px;
        margin-bottom: 20px;
      }
    }
    .w150 {
      width: 150px;
    }
    li:nth-child(2) {
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
}
</style>



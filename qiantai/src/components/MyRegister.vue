<template>
    <div id="register">
      <el-dialog title="注册" width="300px" center :visible.sync="isRegister">
        <el-form :model="RegisterUser" :rules="rules" status-icon ref="ruleForm" class="demo-ruleForm">
          <!-- 用户名输入框 -->
          <el-form-item prop="name">
            <el-input prefix-icon="el-icon-user-solid" placeholder="请输入账号" v-model="RegisterUser.name"></el-input>
          </el-form-item>
  
          <!-- 密码输入框 -->
          <el-form-item prop="pass">
            <el-input prefix-icon="el-icon-view" type="password" placeholder="请输入密码" v-model="RegisterUser.pass"></el-input>
          </el-form-item>
  
          <!-- 确认密码输入框 -->
          <el-form-item prop="confirmPass">
            <el-input prefix-icon="el-icon-view" type="password" placeholder="请再次输入密码" v-model="RegisterUser.confirmPass"></el-input>
          </el-form-item>
  
          <!-- 用户角色选择 -->
          <el-form-item prop="role">
            <el-radio v-model='RegisterUser.role' label='用户'>用户</el-radio>
          </el-form-item>
  
          <!-- 注册按钮 -->
          <el-form-item>
            <el-button size="medium" type="primary" @click="Register" style="width:100%;">注册</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
  </template>
  
  <script>
  export default {
    name: "MyRegister",
    props: ["register"],
    data() {
      // 用户名的校验方法
      let validateName = (rule, value, callback) => {
        if (!value) {
          return callback(new Error("请输入用户名"));
        }
        // 用户名以字母开头,长度在5-16之间,允许字母数字下划线
        const userNameRule = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
        if (userNameRule.test(value)) {
          // 检查数据库中是否已经存在该用户名
          this.checkUserNameExist(value, callback);
        } else {
          return callback(new Error("字母开头,长度5-16之间,允许字母数字下划线"));
        }
      };
  
      // 密码的校验方法
      let validatePass = (rule, value, callback) => {
        if (value === "") {
          return callback(new Error("请输入密码"));
        }
        // 密码以字母开头,长度在6-18之间,允许字母数字和下划线
        const passwordRule = /^[a-zA-Z]\w{5,17}$/;
        if (passwordRule.test(value)) {
          this.$refs.ruleForm.validateField("checkPass");
          return callback();
        } else {
          return callback(
            new Error("字母开头,长度6-18之间,允许字母数字和下划线")
          );
        }
      };
  
      // 确认密码的校验方法
      let validateConfirmPass = (rule, value, callback) => {
        if (value === "") {
          return callback(new Error("请输入确认密码"));
        }
        // 校验是否与密码一致
        if (this.RegisterUser.pass != "" && value === this.RegisterUser.pass) {
          this.$refs.ruleForm.validateField("checkPass");
          return callback();
        } else {
          return callback(new Error("两次输入的密码不一致"));
        }
      };
  
      return {
        isRegister: false, // 控制注册组件是否显示
        RegisterUser: {
          name: "",
          pass: "",
          confirmPass: "",
          role: "用户"
        },
        // 用户信息校验规则,validator(校验方法),trigger(触发方式),blur为在组件 Input 失去焦点时触发
        rules: {
          name: [{ validator: validateName, trigger: "blur" }],
          pass: [{ validator: validatePass, trigger: "blur" }],
          confirmPass: [{ validator: validateConfirmPass, trigger: "blur" }]
        }
      };
    },
    watch: {
      // 监听父组件传过来的register变量，设置this.isRegister的值
      register: function(val) {
        if (val) {
          this.isRegister = val;
        }
      },
      // 监听this.isRegister变量的值，更新父组件register变量的值
      isRegister: function(val) {
        if (!val) {
          this.$refs["ruleForm"].resetFields();
          this.$emit("fromChild", val);
        }
      }
    },
    methods: {
      // 检查用户名是否已存在
      checkUserNameExist(username, callback) {
        this.$axios
          .post("/api/checkUsername", { username })
          .then((response) => {
            if (response.data.code === "0") {
              // 用户名存在，提示错误
              callback(new Error("用户名已存在"));
            } else {
              callback(); // 用户名可用
            }
          })
          .catch((error) => {
            console.error(error);
            callback(new Error("检查用户名是否存在时发生错误"));
          });
      },
      // 注册方法
      Register() {
        // 通过element自定义表单校验规则，校验用户输入的用户信息
        this.$refs["ruleForm"].validate((valid) => {
          if (valid) {
            // 发送注册请求
            this.$axios
              .post("/api/front/registeryonghu", {
                username: this.RegisterUser.name,
                password: this.RegisterUser.pass,
                role: this.RegisterUser.role
              })
              .then((res) => {
                if (res.data.code === "0") {
                  this.$message.success("注册成功！");
                  this.isRegister = false;
                  this.$emit("fromChild", false); // 通知父组件关闭注册框
                } else {
                  this.$message.error(res.data.msg);
                }
              })
              .catch((err) => {
                this.$message.error("注册失败，请稍后再试");
              });
          } else {
            return false;
          }
        });
      }
    }
  };
  </script>
  
  <style scoped>
    .login-code {
      cursor: pointer;
      font-size: 18px;
      font-weight: bold;
      color: #409EFF;
    }
  
    .login-code-img {
      font-size: 20px;
      font-weight: bold;
    }
  </style>
  
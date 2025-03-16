<template>
    <div id="myLogin">
      <el-dialog title="登录" width="300px" center :visible.sync="isLogin">
        <el-form :model="LoginUser" :rules="rules" status-icon ref="ruleForm" class="demo-ruleForm">
          <el-form-item prop="name">
            <el-input prefix-icon="el-icon-user-solid" placeholder="请输入账号" v-model="LoginUser.name"></el-input>
          </el-form-item>
          <el-form-item prop="pass">
            <el-input prefix-icon="el-icon-view" type="password" placeholder="请输入密码" v-model="LoginUser.pass"></el-input>
          </el-form-item>
          <!-- 添加验证码输入框 -->
          <el-form-item label="" :rules="[{ required: true, message: '请输入验证码', trigger: 'blur' }]">
            <el-row>
              <el-col :span="16" class="el-col el-col-16">
                <el-input prefix-icon="el-icon-position" v-model="LoginUser.captcha" placeholder="请输入验证码" ></el-input>
              </el-col>
              <el-col :span="8" style="text-align: center;" class="el-col el-col-8">
                <div class="login-code"><span class="login-code-img" @click="generateCaptcha">{{captcha}}</span></div>
            </el-col>
            </el-row>
          </el-form-item>
          <el-form-item prop="role">
            <el-radio v-model='LoginUser.role' label='用户'>用户</el-radio>
          </el-form-item>
          <el-form-item>
            <el-button size="medium" type="primary" @click="Login" style="width:100%;">登录</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
  </template>
  
  <script>
    import { mapActions } from "vuex";
  
    export default {
      name: "MyLogin",
      data() {
        return {
          rules: {
            name: [
              { required: true, message: '请输入用户名', trigger: 'blur' }
            ],
            pass: [
              { required: true, message: '请输入密码', trigger: 'blur' }
            ],
            captcha: [
              { required: true, message: '请输入验证码', trigger: 'blur' }
            ],
          },
          LoginUser: {
            name: "",
            pass: "",
            role: "用户",
            captcha: ""  // 用于绑定验证码输入值
          },
          captcha: "",  // 用于显示验证码
        };
      },
      computed: {
        isLogin: {
          get() {
            return this.$store.getters.getShowLogin;
          },
          set(val) {
            this.$refs["ruleForm"].resetFields();
            this.setShowLogin(val);
          }
        }
      },
      methods: {
        ...mapActions(["setUser", "setShowLogin"]),
        
        // 生成四位随机验证码
        generateCaptcha() {
          this.captcha = Math.floor(1000 + Math.random() * 9000).toString();  // 生成四位随机数字
        },
  
        Login() {
          let username = this.LoginUser.name;
          let cx = this.LoginUser.role;
          // 通过element自定义表单校验规则，校验用户输入的用户信息
          this.$refs["ruleForm"].validate(valid => {
            // 如果通过校验开始登录
            if (valid) {
              // 校验验证码是否正确
              if (this.LoginUser.captcha !== this.captcha) {
                this.$refs["ruleForm"].resetFields();
                this.$message.error("验证码不正确！");
                return;
              }

              if (this.LoginUser.role === '用户') {
                this.$axios.post("/api/front/loginyonghu", {
                  yonghubianhao: this.LoginUser.name,
                  mima: this.LoginUser.pass
                })
                .then(res => {
                  if (res.data.code === "0") {
                    this.isLogin = false;
                    let user = JSON.stringify(res.data.data);
                    localStorage.setItem("user", user);
                    this.setUser(res.data.data.user);
                    localStorage.setItem("username", username);
                    localStorage.setItem("cx", cx);
                    this.$message.success(res.data.msg);
                    this.$router.go(0)
                  } else {
                    this.$refs["ruleForm"].resetFields();
                    this.$message.error(res.data.msg);
                  }
                })
                .catch(err => {
                  return Promise.reject(err);
                });
              }
            } else {
              return false;
            }
          });
        }
      },
      created() {
        // 初次加载时生成验证码
        this.generateCaptcha();
      }
    };
</script>
<style scoped>
.login-code {
    display: flex;
    align-items: center;
    justify-content: space-around;
    margin: 0 0 0 10px;
    user-select: none;
}
.login-code-img {
			margin-top: 2px;
			width: 100px;
			height: 38px;
			border: 1px solid var(--prev-border-color-base);
			color: var(--prev-color-text-primary);
			font-size: 14px;
			font-weight: 700;
			letter-spacing: 5px;
			line-height: 38px;
			text-indent: 5px;
			text-align: center;
			cursor: pointer;
			transition: all ease 0.2s;
			border-radius: 4px;
			/* &:hover {
				border-color: var(--prev-border-color-hover);
				transition: all ease 0.2s;
			} */
		}
</style>

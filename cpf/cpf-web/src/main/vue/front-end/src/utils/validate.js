/**
 * 检验是否为百分比 ，在0~100之间的浮点数
 * @param {*} rule 检验规则
 * @param {*} value 检验值
 * @param {*} callback 回调函数
 */
export const checkPercent = (rule, value, callback) => {
    var reg = new RegExp("^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$");
    if (!value) {
      //可为空，代表条件不限制
      return callback();
    }
    setTimeout(() => {
      if (!reg.test(value)) {
        callback(new Error("请输入正数"));
      } else {
        let num = Number(value);
        if (value > 100 || value < 0) {
          callback(new Error("0-100之间"));
        } else {
          callback();
        }
      }
    }, 1000);
  };
/**
 * 检验是否为正整数
 * @param {*} rule 检验规则
 * @param {*} value 检验值
 * @param {*} callback 回调函数
 */
  export const checkInteger = (rule, value, callback) => {
    var reg = new RegExp("^[0-9]*$");
    if (!value) {
      //可为空，代表条件不限制
      return callback();
    }
    setTimeout(() => {
      if (!reg.test(value)) {
        callback(new Error("请输入正整数"));
      }else{
        callback();
      }
    }, 1000);
  };

  /**
 * 检验是否为正浮点数字 ，
 * @param {*} rule 检验规则
 * @param {*} value 检验值
 * @param {*} callback 回调函数
 */
export const checkDouble = (rule, value, callback) => {
  var reg = new RegExp("^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$");
  if (!value) {
    //可为空，代表条件不限制
    return callback();
  }
  setTimeout(() => {
    if (!reg.test(value)) {
      callback(new Error("请输入正浮点数"));
    } else{
      callback();
    }
  }, 1000);
};


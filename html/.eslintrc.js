// http://eslint.org/docs/user-guide/configuring

module.exports = {
  root: true,
  parser: 'babel-eslint',
  parserOptions: {
    sourceType: 'module'
  },
  env: {
    browser: true,
  },
  // https://github.com/feross/standard/blob/master/RULES.md#javascript-standard-style
  extends: 'standard',
  // required to lint *.vue files
  plugins: [
    'html'
  ],
  'rules': {
    'import/no-unresolved': 0,
    // allow debugger during development
    'no-debugger': process.env.NODE_ENV === 'production' ? 2 : 0,
    'no-console': 0,
    'no-unused-vars': [1, {'args': 'none'}], // 有未使用的变量也是只警告
    'semi': [2, 'never'],
    'object-curly-spacing': [2, 'never'],
    'comma-dangle': [1, 'always-multiline'],
    'no-unused-expressions': [2, {'allowShortCircuit': true, 'allowTernary': true}],
    'prefer-const': 0,
    'prefer-template': 0,
    'prefer-arrow-callback': 0,
    'max-len': [0, 120],
    'space-before-function-paren': [2, {'anonymous': 'ignore', 'named': 'always'}],
    'object-shorthand': 0,
    'no-param-reassign': [2, {'props': false}],
    'global-require': 0,
    'brace-style': [2, '1tbs', {'allowSingleLine': true}],
    'arrow-body-style': [2, 'always'],
    'func-names': [2, 'never'],
    'no-trailing-spaces': 0, //一行结束后面不要有空格
    'no-useless-escape': 0, // 不检查转义符
    'key-spacing': [1, {'mode': 'minimum'}], // 空格太多的时候只警告
    'default-case': 0,
    'padded-blocks': 0, // 多余的空行只做警告
  },
}

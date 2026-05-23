// HTML コンテンツを設定
export const getCcsSsoHtmlContent = function(form, params) {
  return `
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml">
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="Content-Style-Type" content="text/css" />
        <meta http-equiv="Content-Script-Type" content="text/javascript" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
        <meta name="Description" content="IFAポータルサイト" />
        <link rel="shortcut icon" href="https://a248.e.akamai.net/f/248/29350/7d/pict.sbisec.co.jp/sbisec/images/base/favicon.ico" type="image/vnd.microsoft.icon" />
        <title>IFAポータル</title>
      </head>
      <body>
        <script type="text/javascript">
          async function disableWindow() {
            const winName = "disableWindow";
            const winOpt  = "top=0, left=2, height=738, width=1013, location=no, menubar=no, toolbar=no, status=no, scrollbars=yes, resizable=yes";

            await sleep(300);

            window.open("${params.logoutUrl}", winName, winOpt);

            await sleep(300);

            document.forms["ccsLoginForm"].submit();

            await sleep(300);

            const tempUrl = ${params.tempUrl ? `"${params.tempUrl}"` : "''"};
            if (tempUrl) {
              window.open(tempUrl, winName, winOpt);
              await sleep(300);
            }

            window.open("${params.transitionUrl}", winName, winOpt);
          }

          function doLogin() {
            disableWindow();
          }

          function sleep(time) {
            return new Promise(resolve => setTimeout(resolve, time));
          }

          window.onload = doLogin;
        </script>
        <form name="ccsLoginForm" action="${params.loginUrl}" method="POST" target="disableWindow">
          <input type="hidden" name="j_username" value="${form.ccsUserId}"/>
          <input type="hidden" name="j_password" value="${form.ccsUserPw}"/>
          <input type="hidden" name="j_ipaddrs"  value="dummy"/>
          <p style="font-size: larger;font-weight: 550;">CCSとの連携に必要なため、「×」で閉じないでください。
（「ー」によるウィンドウ最小化は問題ありません）</p>
        </form>
      </body>
    </html>
  `
}

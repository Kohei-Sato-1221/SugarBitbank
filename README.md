# SugarBitbank  
これはBitbankAPIを用いて仮想通貨の自動売買を行うプログラムです。  
BitbankのサンプルAPIをベースに開発されているプログラムです。  
APIキーや設定ファイルは外出となっています。  
  
また、発注した内容はGoogle App Scriptと連携しており、  
Gmailに通知が飛ぶような設定になっています。

# todo:
1. 依存性の脆弱性解決
2. いい感じで利益出すようなロジックを考える（DBで利益管理）
3. Web Consoleを作って、設定画面用意
4. Spring Boot化する(cronでのスケジューラー管理やめる)

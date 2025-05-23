リアルMD用 Androidアプリ
=============

三和様向けに作成したMDシステムのハンディー端末アプリ（WindowsCE版。以下PDAアプリ）
の1部機能をAndroidスマホで動作するように新規作成する為の検証用アプリ

開発対象機能
-------

発注機能に限定し下記機能を対象とする  

- メニュー
- 発注日便指定
- 商品指定納品数設定
- 発注一覧（転送前の発注データ）
- 発注データ送信（発注データのストレージ出力）
- 商品マスタ受信

Android端末実機を利用できないため、データの取込みと出力はストレージから行う。
バーコード読取、アプリの配布インストールも行わない。

各機能の開発状況は後述

開発環境
-------

### 開発端末
INES AVD  
補足：AWS Workspaceの利用を検討したが、
AWS WorkspaceのCPUはV-TXをサポートしていなかったため、仮想デバイスが動作しなかった。
#### スペック
メモリ：16GB  
補足：メモリ16GBだと、Android Studioの動作が遅く、仮想デバイスはAndroid12までしか動作しない。開発に際しては32GBのメモリは必須。

必要ツールのインストールにはDisk空き容量は30GB必要
#### 開発ツール
- Android Studio 2024.3.2
- DB Browser for SQLite バージョン 3.13.1

開発環境を再現する際は、最新のバージョンを入れるのが良い  
（Android Studio自体アップデートを促すし、  
プロジェクト作成時にテンプレートがその時点の標準的なもののため）

### 開発プラットフォーム

- 言語：Kotlin
- プラットフォーム：Jetpack Compose
- DBライブラリ：Room
- 内部DB：SQLite

#### ライブラリ

標準のEmpty Activity プロジェクトに追加したライブラリ

- androidx-navigation-compose（画面遷移用）
- kotlinx-serialization-json（画面遷移用）
- androidx-room-runtime（DBアクセス）
- androidx-room-compiler（DBアクセス）
- androidx-room-ktx（DBアクセス）
- androidx-material-icons-extended（拡張アイコン）
- androidx-lifecycle-viewmodel-compose（ViewModel用、現時点で未使用）


### プロジェクト構成

#### パッケージ構成と説明

##### db
DBアクセス関連のエンティティ、DAO、Repository、Container、Applicationを配置
Application、Containerは、DBアクセスを別スレッドでシングルトンで動作させるために必要

##### navigation
画面の切替を行うNavHostを配置

##### ui.deliverydate
発注日、便を設定する画面レイアウトは配置  
ui.画面名のパッケージ内にはViewModelも格納する予定

##### ui.menu
メニュー画面レイアウトは配置

##### ui.orderdate
納品数を設定する画面レイアウトは配置

##### ui.theme
アプリの配色などのテーマを配置。今回は未使用

##### ui.tryout
検証用試作画面レイアウトは配置
現在は、DBへのインサート処理を記述

パッケージ構成図

```auto
.
└── com.exsample.orderterminal
    ├── db
    ├── navigation
    ├── ui
    │   ├── deliverydate
    │   ├── menu
    │   ├── orderdate
    │   ├── theme
    │   └── tryout
    └── MainActivity.kt
```

#### ソース外で修正したファイル

##### libs.versions.toml,build.gradle.kts
追加ライブラリをの追記

##### AndroidManifest.xml
RoomApplicationの設定を追記

現状と残課題
------------

### メニュー
「発注（納品日指定）」ボタン押下で発注便指定に遷移

### ボトムバー
ホームアイコン各画面からでメニューに戻る  
設定画面の遷移未実装（設定画面実装）

### 発注日便指定
日付選択のダイアログは開くが、選択した日付をTextFieldに反映していない  
「納品数設定へ」ボタンで商品指定納品数設定に遷移  
発注日、便指定の値を保持、次画面への移送をしていない  
発注一覧への画面遷移（発注一覧自体未実装）

### 商品指定納品数設定
画面の値は全て直接記述（Mock状態）

### 発注一覧（転送前の発注データ）
未実装

### 発注データ送信、商品マスタ受信、設定
未実装

### DB
データソースの設定、初期インストール時のDB作成、登録したデータの反映を確認  
商品テーブルは項目は、コード、名前、規格、売価のみ  
（DB値を画面に表示出来た後に項目整備）  
テーブルに対してインデックスの作成はしていない

### 上記以外の次実装内容一覧

- DBからのデータを保持するためにViewModel＋UiStateの実装
- 入力、選択した値を次画面に受け渡す処理
- テーマを利用した色調設定（これは保留）
- 納品数を設定するために、数値のみを入力する電卓のようなダイアログが欲しい

店舗コードや端末識別の設定方法案
Android端末は、IMESという端末固有のIDを持っています。
アプリをダウンロードしてインストールした後に、  
アプリからIMESを表示して、その値をサーバーアプリに登録  
サーバーアプリは、登録されたIMESからのアクセスのみ  
受付ける作りにすれば良いと思われます。

アプリのダウンロード自体の制御は、ダウンロードしたアプリをAndoroid端末から  
抜き出してコピーが出来るので、インストール後のアクセス制限を設ける必要がある。





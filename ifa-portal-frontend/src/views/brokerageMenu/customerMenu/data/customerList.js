// 投信銘柄リスト
exports.getCustomerList = () => {
  return [
    {
      type: '個人',
      name: '藤川静枝',
      nameKana: 'フジカワシズエ',
      gender: '女性',
      customerId: '11119198',
      accountStatus: '既得意',
      accountNumber: 'Z52-0001774',
      branchCd: 'Z52',
      accountNo: '0001774',
      accountInfo: '国内信用 特定源徴有 配受無 JrNISA',
      accountOpenDate: '2022/01/23',
      jrIsaContractType: '1',
      jrNisaAccountNumber: 'Z52-0001775',
      jrNisaAccountInfo: '',
      jrNisaAccountOpenDate: '2022/01/23',
      preferentialRate: '10.0',
      favorite: true,
      addressKana: 'ｷﾞﾌｹﾝﾄｻｼﾋﾀﾞﾁｮｳｱｻﾉ934ﾊﾞﾝﾁ20ｺﾞｳ',
      address: '岐阜県土岐市肥田町浅野934番地20号',
      postalCode: '509-5115',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '無',
      memo: {
        text: 'メモを表示｡\n顧客メモ情報変更で編集する｡\nメモは半角文字に換算して1500文字が記載可能｡\nダブルクリックで編集可能｡',
        last: '2016/03/08 12:12:12'
      },
      withdrawalRestrictionCancelFlag: '1'
    },
    {
      type: '個人',
      name: '津村米吉',
      nameKana: 'ツムラヨネキチ',
      gender: '男性',
      customerId: '11119199',
      accountStatus: '既得意',
      accountNumber: 'Z52-0001776',
      branchCd: 'Z52',
      accountNo: '0001776',
      accountInfo: '特定源徴有 JrNISA',
      accountOpenDate: '2022/01/05',
      jrIsaContractType: '1',
      jrNisaAccountNumber: 'Z52-0001777',
      jrNisaAccountInfo: '',
      jrNisaAccountOpenDate: '2022/01/23',
      preferentialRate: '5.5',
      favorite: true,
      addressKana: 'ｲｼｶﾜｹﾝﾊｸｲｸﾞﾝｼｶﾞﾏﾁｵｵｻｶ358ﾊﾞﾝﾁ20ｺﾞｳ',
      address: '石川県羽咋郡志賀町大坂358番地20号',
      postalCode: '925-0133',
      noteLimit: [{
        no: '001',
        content: '国内株式現物買い（日中）',
        date: '2021/11/02 10:00'
      },
      {
        no: '002',
        content: '国内株式現物買い（PTS）',
        date: '2021/11/02 10:00'
      }
      ],
      noteInfo: [
        {
          class: '米国株式売却停止',
          memo: '外貨赤残発生原因につき売却停止',
          date: '2021/11/02',
          owner: 'madmin'
        },
        {
          class: 'マイナンバー未受入（取引制限中）',
          memo: '【EX口座開設】全取引停止',
          date: '2016/10/24',
          owner: 'BTUSR'
        },
        {
          class: '国籍取引制限あり',
          memo: '国籍：（）　|　米国永住権（グリーンカード）の保有なし　|　米国居住者でない　',
          date: '2020/02/17',
          owner: 'BTUSR'
        },
        {
          class: '届出印未登録',
          memo: '「都度本人確認書類の提出と照合」による本人確認',
          date: '2016/10/24',
          owner: 'BTUSR'
        },
        {
          class: '投資信託売却停止',
          memo: 'テスト',
          date: '2021/08/26',
          owner: '仲山 雄二'
        }
      ],
      foreignBuyingPower: '有（3カ国）',
      memo: {
        text: 'メモを表示｡\n顧客メモ情報変更で編集する｡\nメモは半角文字に換算して1500文字が記載可能｡\nダブルクリックで編集可能｡',
        last: '2011/11/11 11:11:11'
      }
    },
    {
      type: '個人',
      name: '北山裕',
      nameKana: 'キタヤマヒロシ',
      gender: '男性',
      customerId: '11119200',
      accountStatus: '既得意',
      accountNumber: 'Z41-0001778',
      branchCd: 'Z41',
      accountNo: '0001778',
      accountInfo: '国内信用 特定源徴有 配受無 積立NISA',
      accountOpenDate: '2015/01/08',
      jrIsaContractType: '',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: true,
      addressKana: 'ｷｮｳﾄﾌｷｮｳﾄｼﾐﾅﾐｸｶﾗﾊｼﾀｶﾀﾞﾁｮｳ470ﾊﾞﾝﾁ19ｺﾞｳ',
      address: '京都府京都市南区唐橋高田町470番地19号',
      noteLimit: [{
        no: '001',
        content: '国内株式現物買い（日中）',
        date: '2021/11/02 10:00'
      },
      {
        no: '002',
        content: '国内株式現物買い（PTS）',
        date: '2021/11/02 10:00'
      }
      ],
      noteInfo: [],
      foreignBuyingPower: '無',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '河口悟',
      nameKana: 'カワグチサトル',
      gender: '男性',
      customerId: '11119201',
      accountStatus: '既得意',
      accountNumber: 'Z51-0001780',
      branchCd: 'Z51',
      accountNo: '0001780',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2015/05/10',
      jrIsaContractType: '',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      addressKana: 'ﾆｲｶﾞﾀｹﾝﾄｵｶﾏﾁｼﾑﾛﾉ311ﾊﾞﾝﾁ4ｺﾞｳ',
      address: '新潟県十日町市室野311番地4号',
      noteLimit: [{
        no: '001',
        content: '国内株式信用新規（日中）',
        date: '2021/11/02 10:00'
      }
      ],
      noteInfo: [
        {
          class: '投資信託売却停止',
          memo: 'テスト',
          date: '2021/08/26',
          owner: '仲山 雄二'
        }
      ],
      foreignBuyingPower: '有（3カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '村山美明',
      nameKana: 'ムラヤマヨシアキ',
      gender: '男性',
      customerId: '11119202',
      accountStatus: '既得意',
      accountNumber: '129-0001782',
      branchCd: '129',
      accountNo: '0001782',
      accountInfo: 'マル優 NISA',
      accountOpenDate: '2016/07/17',
      jrIsaContractType: '',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '15.6',
      favorite: false,
      addressKana: 'ﾋﾛｼﾏｹﾝﾊﾂｶｲﾁｱｼﾅ4ﾁｮｳﾒ97ﾊﾞﾝﾁ17ｺﾞｳ',
      address: '広島県廿日市市阿品4丁目97番地17号',
      postalCode: '738-0054',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（3カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '浦田愛奈',
      nameKana: 'ウラタマナ',
      gender: '女性',
      customerId: '11119203',
      accountStatus: '閉鎖',
      accountNumber: 'Z52-0001784',
      branchCd: 'Z52',
      accountNo: '0001784',
      accountInfo: '国内信用 特定源徴無',
      accountOpenDate: '2015/09/14',
      jrIsaContractType: '',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: true,
      addressKana: 'ｷｮｳﾄﾌﾔﾊﾀｼﾔﾊﾀｼｷﾌﾞﾔ647ﾊﾞﾝﾁ3ｺﾞｳ',
      address: '京都府八幡市八幡式部谷647番地3号',
      postalCode: '614-8061',
      noteLimit: [
        {
          no: '001',
          content: '国内株式信用新規（PTS）',
          date: '2021/11/02 10:00'
        }
      ],
      noteInfo: [
        {
          class: '国籍取引制限あり',
          memo: '国籍：（）　|　米国永住権（グリーンカード）の保有なし　|　米国居住者でない　',
          date: '2020/02/17',
          owner: 'BTUSR'
        }
      ],
      foreignBuyingPower: '有（4カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '蛭田千咲',
      nameKana: 'ヒルタチサキ',
      gender: '',
      customerId: '11119204',
      accountStatus: '既得意',
      accountNumber: 'Z52-0001786',
      branchCd: 'Z52',
      accountNo: '0001786',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2013/12/15',
      jrIsaContractType: '',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '京都府京都市山科区御陵牛尾町411番地18号',
      postalCode: '607-8418',
      noteLimit: [{
        no: '001',
        content: '国内株式現物買い（日中）',
        date: '2021/11/02 10:00'
      },
      {
        no: '002',
        content: '国内株式現物買い（PTS）',
        date: '2021/11/02 10:00'
      }
      ],
      noteInfo: [],
      foreignBuyingPower: '有（4カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '吉澤浩司',
      nameKana: 'ヨシザワコウジ',
      gender: '男性',
      customerId: '11119205',
      accountStatus: '既得意',
      accountNumber: 'Z52-0001788',
      branchCd: 'Z52',
      accountNo: '0001788',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2020/12/21',
      jrIsaContractType: '',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '大阪府泉南市兎田954番地3号',
      postalCode: '590-0501',
      noteLimit: [],
      noteInfo: [
        {
          class: '投資信託売却停止',
          memo: 'テスト',
          date: '2021/08/26',
          owner: '仲山 雄二'
        }
      ],
      foreignBuyingPower: '有（4カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '森永莉沙',
      nameKana: 'モリナガリサ',
      gender: '女性',
      customerId: '11119206',
      accountStatus: '既得意',
      accountNumber: 'Z56-0596799',
      branchCd: 'Z56',
      accountNo: '0596799',
      accountInfo: '国内信用 特定源徴有 配受無 JrNISA',
      accountOpenDate: '2013/08/21',
      jrIsaContractType: '1',
      jrNisaAccountNumber: 'Z41-0001791',
      jrNisaAccountInfo: '',
      jrNisaAccountOpenDate: '2018/07/17',
      preferentialRate: '',
      favorite: true,
      address: '福島県郡山市田村町田母神41番地16号',
      postalCode: '963-1243',
      noteLimit: [
        {
          no: '001',
          content: '国内株式現物買い（日中）'
        }
      ],
      noteInfo: [
        {
          class: 'マイナンバー未受入（取引制限中）',
          memo: '【EX口座開設】全取引停止',
          date: '2016/10/24',
          owner: 'BTUSR'
        }
      ],
      foreignBuyingPower: '有（4カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '久保和雄',
      nameKana: 'クボカズオ',
      gender: '男性',
      customerId: '11119207',
      accountStatus: '既得意',
      accountNumber: 'Z56-0896080',
      branchCd: 'Z56',
      accountNo: '0896080',
      accountInfo: '国内信用 外国株式 特定源徴有 配受無',
      accountOpenDate: '2013/04/08',
      jrIsaContractType: '',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '熊本県八代市錦町602番地5号',
      postalCode: '866-0834',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（4カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '徳永勇夫',
      nameKana: 'トクナガイサオ',
      gender: '男性',
      customerId: '11119208',
      accountStatus: '既得意',
      accountNumber: '129-0001794',
      branchCd: '129',
      accountNo: '0001794',
      accountInfo: '米株信用 特定源徴有 配受無',
      accountOpenDate: '2015/05/27',
      jrIsaContractType: '',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '福岡県筑後市古島554番地19号',
      postalCode: '833-0035',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（4カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '小高舞衣',
      nameKana: 'コタカマイ',
      gender: '女性',
      customerId: '11119209',
      accountStatus: '既得意',
      accountNumber: 'Z52-0001796',
      branchCd: 'Z52',
      accountNo: '0001796',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2016/01/14',
      jrIsaContractType: '',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '奈良県桜井市生田590番地18号',
      postalCode: '633-0048',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（4カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '佐竹莉緒',
      nameKana: 'サタケリオ',
      gender: '女性',
      customerId: '11119210',
      accountStatus: '既得意',
      accountNumber: 'Z52-0001798',
      branchCd: 'Z52',
      accountNo: '0001798',
      accountInfo: '米株信用 特定源徴有 配受無 JrNISA',
      accountOpenDate: '2011/12/04',
      jrIsaContractType: '1',
      jrNisaAccountNumber: 'Z52-0001799',
      jrNisaAccountInfo: '',
      jrNisaAccountOpenDate: '2013/09/23',
      preferentialRate: '',
      favorite: false,
      address: '京都府京都市上京区東上善寺町708番地7号',
      postalCode: '602-8468',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（4カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '西沢謙二',
      nameKana: 'ニシザワケンジ',
      gender: '',
      customerId: '11119211',
      accountStatus: '既得意',
      accountNumber: 'Z52-0001800',
      branchCd: 'Z52',
      accountNo: '0001800',
      accountInfo: '米株信用 特定源徴有 配受無',
      accountOpenDate: '2017/03/23',
      jrIsaContractType: '',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '秋田県男鹿市戸賀戸賀133番地10号',
      postalCode: '602-8468',
      noteLimit: [],
      noteInfo: [
        {
          class: '国籍取引制限あり',
          memo: '国籍：（）　|　米国永住権（グリーンカード）の保有なし　|　米国居住者でない　',
          date: '2020/02/17',
          owner: 'BTUSR'
        },
        {
          class: '届出印未登録',
          memo: '「都度本人確認書類の提出と照合」による本人確認',
          date: '2016/10/24',
          owner: 'BTUSR'
        }
      ],
      foreignBuyingPower: '有（4カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '田所怜子',
      nameKana: 'タドコロレイコ',
      gender: '女性',
      customerId: '11119212',
      accountStatus: '既得意',
      accountNumber: 'Z41-0001802',
      branchCd: 'Z41',
      accountNo: '0001802',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2017/12/02',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '茨城県つくば市田倉832番地8号',
      postalCode: '300-2615',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（4カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '中沢昭子',
      nameKana: 'ナカザワアキコ',
      gender: '女性',
      customerId: '11119213',
      accountStatus: '既得意',
      accountNumber: 'Z44-0001804',
      branchCd: 'Z44',
      accountNo: '0001804',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2014/07/30',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '愛知県安城市和泉町134番地7号',
      postalCode: '444-1221',
      noteLimit: [],
      noteInfo: [
        {
          class: 'マイナンバー未受入（取引制限中）',
          memo: '【EX口座開設】全取引停止',
          date: '2016/10/24',
          owner: 'BTUSR'
        }
      ],
      foreignBuyingPower: '有（4カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '柳幸吉',
      nameKana: 'ヤナギコウキチ',
      gender: '男性',
      customerId: '11119214',
      accountStatus: '既得意',
      accountNumber: 'Z51-0001806',
      branchCd: 'Z51',
      accountNo: '0001806',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2018/06/17',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '3.5',
      favorite: false,
      address: '鹿児島県志布志市松山町新橋966番地11号',
      postalCode: '444-1221',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（4カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '新倉愛子',
      nameKana: 'ニイクラアイコ',
      gender: '女性',
      customerId: '11119215',
      accountStatus: '既得意',
      accountNumber: 'Z52-0001808',
      branchCd: 'Z52',
      accountNo: '0001808',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2020/08/13',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '茨城県潮来市洲崎933番地3号',
      postalCode: '311-2408',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（4カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '香坂秀樹',
      nameKana: 'コウサカヒデキ',
      gender: '男性',
      customerId: '11119216',
      accountStatus: '既得意',
      accountNumber: '129-0001810',
      branchCd: '129',
      accountNo: '0001810',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2011/04/24',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '8.8',
      favorite: true,
      address: '北海道空知郡上富良野町西７線北510番地12号',
      postalCode: '071-0541',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（4カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '日野遥奈',
      nameKana: 'ヒノハルナ',
      gender: '女性',
      customerId: '11119217',
      accountStatus: '既得意',
      accountNumber: 'Z41-0001812',
      branchCd: 'Z41',
      accountNo: '0001812',
      accountInfo: '国内信用 特定源徴無 配受無',
      accountOpenDate: '2011/06/07',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '高知県高岡郡佐川町乙984番地9号',
      postalCode: '789-1202',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（4カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '深田武彦',
      nameKana: 'フカダタケヒコ',
      gender: '男性',
      customerId: '11119218',
      accountStatus: '既得意',
      accountNumber: 'Z44-0001814',
      branchCd: 'Z44',
      accountNo: '0001814',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2011/05/27',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '長崎県西海市大島町黒瀬438番地2号',
      postalCode: '857-2401',
      noteLimit: [],
      noteInfo: [
        {
          class: '国籍取引制限あり',
          memo: '国籍：（）　|　米国永住権（グリーンカード）の保有なし　|　米国居住者でない　',
          date: '2020/02/17',
          owner: 'BTUSR'
        },
        {
          class: '投資信託売却停止',
          memo: 'テスト',
          date: '2021/08/26',
          owner: '仲山 雄二'
        }
      ],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '高畑響',
      nameKana: 'タカハタヒビキ',
      gender: '男性',
      customerId: '11119219',
      accountStatus: '既得意',
      accountNumber: 'Z51-0001816',
      branchCd: 'Z51',
      accountNo: '0001816',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2020/08/06',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '長崎県壱岐市芦辺町箱崎谷江触546番地14号',
      postalCode: '811-5464',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '大淵寅雄',
      nameKana: 'オオフチトラオ',
      gender: '男性',
      customerId: '11119220',
      accountStatus: '停止',
      accountNumber: 'Z52-0001818',
      branchCd: 'Z52',
      accountNo: '0001818',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2019/07/27',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '新潟県上越市吉川区入河沢700番地16号',
      postalCode: '949-3421',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '坂下明弘',
      nameKana: 'サカシタアキヒロ',
      gender: '男性',
      customerId: '11119221',
      accountStatus: '既得意',
      accountNumber: '129-0001820',
      branchCd: '129',
      accountNo: '0001820',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2013/04/14',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '愛媛県宇和島市北新町441番地17号',
      postalCode: '798-0010',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '保田晴奈',
      nameKana: 'ヤスダハルナ',
      gender: '女性',
      customerId: '11119222',
      accountStatus: '既得意',
      accountNumber: 'Z41-0001822',
      branchCd: 'Z41',
      accountNo: '0001822',
      accountInfo: '国内信用 特定源徴無 配受無',
      accountOpenDate: '2020/11/29',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '茨城県潮来市延方東420番地20号',
      postalCode: '311-2409',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '石倉莉菜',
      nameKana: 'イシクラリナ',
      gender: '女性',
      customerId: '11119223',
      accountStatus: '閉鎖',
      accountNumber: 'Z44-0001824',
      branchCd: 'Z44',
      accountNo: '0001824',
      accountInfo: '国内信用 特定源徴無 配受無 JrNISA',
      accountOpenDate: '2018/12/29',
      jrNisaAccountNumber: 'Z44-0001825',
      jrNisaAccountInfo: '',
      jrNisaAccountOpenDate: '2019/04/19',
      preferentialRate: '',
      favorite: false,
      address: '兵庫県赤穂市元塩町828番地5号',
      postalCode: '678-0213',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '松田美奈',
      nameKana: 'マツダミナ',
      gender: '女性',
      customerId: '11119224',
      accountStatus: '既得意',
      accountNumber: 'Z51-0001826',
      branchCd: 'Z51',
      accountNo: '0001826',
      accountInfo: '国内信用 特定源徴無 配受無',
      accountOpenDate: '2014/06/13',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '25.0',
      favorite: false,
      address: '千葉県君津市根本649番地8号',
      postalCode: '292-1141',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '高瀬菫',
      nameKana: 'タカセスミレ',
      gender: '女性',
      customerId: '11119225',
      accountStatus: '既得意',
      accountNumber: 'Z52-0001828',
      branchCd: 'Z52',
      accountNo: '0001828',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2019/10/18',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '新潟県新潟市秋葉区野方272番地16号',
      postalCode: '956-0053',
      noteLimit: [],
      noteInfo: [
        {
          class: 'マイナンバー未受入（取引制限中）',
          memo: '【EX口座開設】全取引停止',
          date: '2016/10/24',
          owner: 'BTUSR'
        },
        {
          class: '届出印未登録',
          memo: '「都度本人確認書類の提出と照合」による本人確認',
          date: '2016/10/24',
          owner: 'BTUSR'
        }
      ],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '古屋寅雄',
      nameKana: 'フルヤトラオ',
      gender: '男性',
      customerId: '11119226',
      accountStatus: '既得意',
      accountNumber: '129-0001830',
      branchCd: '129',
      accountNo: '0001830',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2020/10/21',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '宮城県岩沼市桑原西290番地10号',
      postalCode: '987-2447',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '長沢豊明',
      nameKana: 'ナガサワトヨアキ',
      gender: '男性',
      customerId: '11119227',
      accountStatus: '既得意',
      accountNumber: 'Z44-0001832',
      branchCd: 'Z44',
      accountNo: '0001832',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2021/01/21',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '愛媛県越智郡上島町弓削沢津23番地2号',
      postalCode: '794-2502',
      noteLimit: [],
      noteInfo: [
        {
          class: '国籍取引制限あり',
          memo: '国籍：（）　|　米国永住権（グリーンカード）の保有なし　|　米国居住者でない　',
          date: '2020/02/17',
          owner: 'BTUSR'
        }
      ],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '粕谷幸次',
      nameKana: 'カスヤコウジ',
      gender: '男性',
      customerId: '11119228',
      accountStatus: '既得意',
      accountNumber: 'Z51-0001834',
      branchCd: 'Z51',
      accountNo: '0001834',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2011/12/15',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '岐阜県各務原市蘇原申子町1丁目653番地5号',
      postalCode: '504-0845',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '荻原範明',
      nameKana: 'オギワラノリアキ',
      gender: '男性',
      customerId: '11119229',
      accountStatus: '停止',
      accountNumber: 'Z52-0001836',
      branchCd: 'Z52',
      accountNo: '0001836',
      accountInfo: '国内信用 特定源徴有 配受無 JrNISA',
      accountOpenDate: '2015/11/30',
      jrNisaAccountNumber: 'Z52-0001837',
      jrNisaAccountInfo: '',
      jrNisaAccountOpenDate: '2020/08/05',
      preferentialRate: '',
      favorite: false,
      address: '静岡県裾野市稲荷760番地8号',
      postalCode: '410-1113',
      noteInfo: [],
      noteLimit: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '池田啓之',
      nameKana: 'イケダヒロユキ',
      gender: '男性',
      customerId: '11119230',
      accountStatus: '閉鎖',
      accountNumber: 'Z52-0001838',
      branchCd: 'Z52',
      accountNo: '0001838',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2012/07/27',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '大分県別府市鳥越953番地16号',
      postalCode: '874-0817',
      noteInfo: [
        {
          class: 'マイナンバー未受入（取引制限中）',
          memo: '【EX口座開設】全取引停止',
          date: '2016/10/24',
          owner: 'BTUSR'
        }
      ],
      noteLimit: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '進藤敏幸',
      nameKana: 'シンドウトシユキ',
      gender: '男性',
      customerId: '11119231',
      accountStatus: '既得意',
      accountNumber: '129-0001840',
      branchCd: '129',
      accountNo: '0001840',
      accountInfo: '国内信用 特定源徴有 配受無 JrNISA',
      accountOpenDate: '2019/11/17',
      jrNisaAccountNumber: '129-0001841',
      jrNisaAccountInfo: '',
      jrNisaAccountOpenDate: '2020/02/27',
      preferentialRate: '',
      favorite: false,
      address: '新潟県上越市中郷区四ツ屋356番地7号',
      postalCode: '949-2305',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '石垣敦盛',
      nameKana: 'イシガキアツモリ',
      gender: '男性',
      customerId: '11119232',
      accountStatus: '停止',
      accountNumber: 'Z52-0001842',
      branchCd: 'Z52',
      accountNo: '0001842',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2014/05/24',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '長崎県佐世保市世知原町木浦原345番地18号',
      postalCode: '859-6406',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '島田結香',
      nameKana: 'シマダユウカ',
      gender: '女性',
      customerId: '11119233',
      accountStatus: '既得意',
      accountNumber: 'Z52-0001844',
      branchCd: 'Z52',
      accountNo: '0001844',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2011/12/21',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '熊本県玉名郡玉東町山口261番地18号',
      postalCode: '869-0302',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '大城真弓',
      nameKana: 'オオシロマユミ',
      gender: '女性',
      customerId: '11119234',
      accountStatus: '閉鎖',
      accountNumber: 'Z44-0001846',
      branchCd: 'Z44',
      accountNo: '0001846',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2018/11/04',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '福岡県宮若市水原872番地12号',
      postalCode: '822-0151',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '古川次夫',
      nameKana: 'フルカワツグオ',
      gender: '男性',
      customerId: '11119235',
      accountStatus: '既得意',
      accountNumber: 'Z41-0001848',
      branchCd: 'Z41',
      accountNo: '0001848',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2020/09/13',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '山形県鶴岡市羽黒町黒瀬234番地20号',
      postalCode: '997-0151',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '石塚美和子',
      nameKana: 'イシヅカミワコ',
      gender: '女性',
      customerId: '11119236',
      accountStatus: '閉鎖',
      accountNumber: 'Z52-0001850',
      branchCd: 'Z52',
      accountNo: '0001850',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2019/07/15',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '兵庫県三田市三田町32番地6号',
      postalCode: '669-1533',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '杉本美津子',
      nameKana: 'スギモトミツコ',
      gender: '女性',
      customerId: '11119237',
      accountStatus: '既得意',
      accountNumber: 'Z52-0001852',
      branchCd: 'Z52',
      accountNo: '0001852',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2012/12/10',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '10.0',
      favorite: false,
      address: '兵庫県明石市二見町西二見546番地19号',
      postalCode: '674-0094',
      noteLimit: [],
      noteInfo: [
        {
          class: '国籍取引制限あり',
          memo: '国籍：（）　|　米国永住権（グリーンカード）の保有なし　|　米国居住者でない　',
          date: '2020/02/17',
          owner: 'BTUSR'
        },
        {
          class: '投資信託売却停止',
          memo: 'テスト',
          date: '2021/08/26',
          owner: '仲山 雄二'
        },
        {
          class: '届出印未登録',
          memo: '「都度本人確認書類の提出と照合」による本人確認',
          date: '2016/10/24',
          owner: 'BTUSR'
        }
      ],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '江原茂雄',
      nameKana: 'エハラシゲオ',
      gender: '男性',
      customerId: '11119238',
      accountStatus: '停止',
      accountNumber: 'Z44-0001854',
      branchCd: 'Z44',
      accountNo: '0001854',
      accountInfo: '国内信用 特定源徴有 配受無 JrNISA',
      accountOpenDate: '2018/06/21',
      jrNisaAccountNumber: 'Z44-0001855',
      jrNisaAccountInfo: '',
      jrNisaAccountOpenDate: '2019/09/09',
      preferentialRate: '',
      favorite: false,
      address: '愛媛県宇和島市三間町務田924番地4号',
      postalCode: '798-1114',
      noteLimit: [],
      noteInfo: [
        {
          class: 'マイナンバー未受入（取引制限中）',
          memo: '【EX口座開設】全取引停止',
          date: '2016/10/24',
          owner: 'BTUSR'
        }
      ],
      foreignBuyingPower: '有（2カ国）',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '安西好夫',
      nameKana: 'アンザイヨシオ',
      gender: '男性',
      customerId: '11119239',
      accountStatus: '閉鎖',
      accountNumber: 'Z51-0001856',
      branchCd: 'Z51',
      accountNo: '0001856',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2012/05/19',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '富山県富山市草島新町130番地5号',
      postalCode: '930-2207',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '無',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '田畑香奈子',
      nameKana: 'タハタカナコ',
      gender: '女性',
      customerId: '11119240',
      accountStatus: '既得意',
      accountNumber: 'Z52-0001858',
      branchCd: 'Z52',
      accountNo: '0001858',
      accountInfo: '国内信用 特定源徴無 配受無',
      accountOpenDate: '2020/09/28',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '岐阜県岐阜市江口2丁目990番地8号',
      postalCode: '502-0915',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '無',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '大川竜輝',
      nameKana: 'オオカワタツキ',
      gender: '男性',
      customerId: '11119241',
      accountStatus: '停止',
      accountNumber: '129-0001860',
      branchCd: '129',
      accountNo: '0001860',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2011/06/07',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '大阪府松原市南新町4丁目398番地19号',
      postalCode: '580-0023',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '無',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '大沢聖花',
      nameKana: 'オオサワサヤカ',
      gender: '女性',
      customerId: '11119242',
      accountStatus: '停止',
      accountNumber: 'Z41-0001862',
      branchCd: 'Z41',
      accountNo: '0001862',
      accountInfo: '国内信用 特定源徴有 配受無 JrNISA',
      accountOpenDate: '2020/11/08',
      jrNisaAccountNumber: 'Z41-0001863',
      jrNisaAccountInfo: '',
      jrNisaAccountOpenDate: '2020/12/11',
      preferentialRate: '',
      favorite: false,
      address: '栃木県芳賀郡益子町大沢509番地20号',
      postalCode: '321-4104',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '無',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '野口洋希',
      nameKana: 'ノグチヒロキ',
      gender: '男性',
      customerId: '11119243',
      accountStatus: '既得意',
      accountNumber: 'Z44-0001864',
      branchCd: 'Z44',
      accountNo: '0001864',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2012/07/19',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '京都府京都市伏見区指物町689番地20号',
      postalCode: '612-8074',
      noteLimit: [],
      noteInfo: [
        {
          class: '国籍取引制限あり',
          memo: '国籍：（）　|　米国永住権（グリーンカード）の保有なし　|　米国居住者でない　',
          date: '2020/02/17',
          owner: 'BTUSR'
        }
      ],
      foreignBuyingPower: '無',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '寺本美希',
      nameKana: 'テラモトミキ',
      gender: '女性',
      customerId: '11119244',
      accountStatus: '既得意',
      accountNumber: 'Z51-0001866',
      branchCd: 'Z51',
      accountNo: '0001866',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2017/11/01',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '京都府京都市上京区愛染寺町999番地15号',
      postalCode: '602-8278',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '無',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '亀田遥夏',
      nameKana: 'カメダハルカ',
      gender: '女性',
      customerId: '11119245',
      accountStatus: '既得意',
      accountNumber: 'Z52-0001868',
      branchCd: 'Z52',
      accountNo: '0001868',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2019/06/24',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '福島県郡山市不動前1丁目258番地2号',
      postalCode: '963-8042',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '無',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '岡山竜一',
      nameKana: 'オカヤマリュウイチ',
      gender: '',
      customerId: '11119246',
      accountStatus: '閉鎖',
      accountNumber: 'Z52-0001870',
      branchCd: 'Z52',
      accountNo: '0001870',
      accountInfo: '国内信用 特定源徴有 配受無',
      accountOpenDate: '2020/10/04',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '栃木県那須塩原市東遅沢588番地7号',
      postalCode: '329-2761',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '無',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '個人',
      name: '畠中満',
      nameKana: 'ハタナカミツル',
      gender: '男性',
      customerId: '11119247',
      accountStatus: '既得意',
      accountNumber: '129-0001872',
      branchCd: '129',
      accountNo: '0001872',
      accountInfo: '国内信用 特定源徴有 配受無 JrNISA',
      accountOpenDate: '2020/01/08',
      jrNisaAccountNumber: '129-0001873',
      jrNisaAccountInfo: '',
      jrNisaAccountOpenDate: '2020/01/29',
      preferentialRate: '',
      favorite: false,
      address: '新潟県柏崎市市野新田560番地1号',
      postalCode: '945-1251',
      noteLimit: [],
      noteInfo: [
        {
          class: 'マイナンバー未受入（取引制限中）',
          memo: '【EX口座開設】全取引停止',
          date: '2016/10/24',
          owner: 'BTUSR'
        },
        {
          class: '届出印未登録',
          memo: '「都度本人確認書類の提出と照合」による本人確認',
          date: '2016/10/24',
          owner: 'BTUSR'
        }
      ],
      foreignBuyingPower: '無',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '法人',
      name: '株式会社 法人',
      nameKana: 'ﾎｳｼﾞﾝ',
      gender: '',
      customerId: '11119248',
      accountStatus: '既得意',
      accountNumber: 'Z44-0001904',
      branchCd: 'Z44',
      accountNo: '0001904',
      accountInfo: '国内信用',
      accountOpenDate: '2012/05/21',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: true,
      address: '神奈川県川崎市多摩区菅稲田堤4丁目818番地11号',
      postalCode: '214-0003',
      noteLimit: [],
      noteInfo: [
        {
          class: 'マイナンバー未受入（取引制限中）',
          memo: '【EX口座開設】全取引停止',
          date: '2016/10/24',
          owner: 'BTUSR'
        },
        {
          class: '届出印未登録',
          memo: '「都度本人確認書類の提出と照合」による本人確認',
          date: '2016/10/24',
          owner: 'BTUSR'
        }
      ],
      foreignBuyingPower: '無',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '法人',
      name: '株式会社 ○×商事',
      nameKana: 'ﾏﾙﾊﾞﾂｼｮｳｼﾞ',
      gender: '',
      customerId: '11119249',
      accountStatus: '閉鎖',
      accountNumber: 'Z41-0001906',
      branchCd: 'Z41',
      accountNo: '0001906',
      accountInfo: '国内信用',
      accountOpenDate: '2014/07/12',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: false,
      address: '栃木県下都賀郡壬生町中央町346番地9号',
      postalCode: '321-0226',
      noteLimit: [],
      noteInfo: [],
      foreignBuyingPower: '無',
      memo: {
        text: '',
        last: ''
      }
    },
    {
      type: '法人',
      name: '株式会社 △□物流',
      nameKana: 'ｻﾝｶｸｼｶｸﾌﾞﾂﾘｭｳ',
      gender: '',
      customerId: '11119250',
      accountStatus: '停止',
      accountNumber: 'Z52-0001902',
      branchCd: 'Z52',
      accountNo: '0001902',
      accountInfo: '国内信用',
      accountOpenDate: '2014/05/01',
      jrNisaAccountNumber: '',
      jrNisaAccountInfo: '',
      preferentialRate: '',
      favorite: true,
      addressKana: 'ｶﾅｶﾞﾜｹﾝｵﾀﾞﾜﾗｼｲｹｶﾞﾐ100ﾊﾞﾝﾁ3ｺﾞｳ',
      address: '神奈川県小田原市池上100番地3号',
      postalCode: '250-0041',
      noteLimit: [],
      noteInfo: [
        {
          class: 'マイナンバー未受入（取引制限中）',
          memo: '【EX口座開設】全取引停止',
          date: '2016/10/24',
          owner: 'BTUSR'
        },
        {
          class: '届出印未登録',
          memo: '「都度本人確認書類の提出と照合」による本人確認',
          date: '2016/10/24',
          owner: 'BTUSR'
        }
      ],
      foreignBuyingPower: '無',
      memo: {
        text: '',
        last: ''
      }
    }
  ]
}

// memo: 口座開設日は以下のpythonスクリプトにより､'藤川静枝'の2011/03/23を基準に
//       おおよそ10年分の日数の乱数を足して算出しました｡
// import datetime
// import random
// dt1 = datetime.datetime(2011, 3, 23, 0, 0, 0, 0)
// print(dt1 + datetime.timedelta(days=random.dandint(0, 3650)))
//
//       住所は、以下のサイトの架空住所メーカーで作成しました。
//       https://nextvitz.com/jp/post-address-random-1.php

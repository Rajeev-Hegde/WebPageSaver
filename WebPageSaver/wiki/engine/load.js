var mediaWikiLoadStart=(new Date()).getTime();function isCompatible(ua){if(ua===undefined){ua=navigator.userAgent;}return!((ua.indexOf('MSIE')!==-1&&parseFloat(ua.split('MSIE')[1])<7)||(ua.indexOf('Firefox/')!==-1&&parseFloat(ua.split('Firefox/')[1])<3)||ua.match(/BlackBerry[^\/]*\/[1-5]\./)||ua.match(/webOS\/1\.[0-4]/)||ua.match(/PlayStation/i)||ua.match(/SymbianOS|Series60/)||ua.match(/NetFront/)||ua.match(/Opera Mini/)||ua.match(/S40OviBrowser/)||(ua.match(/Glass/)&&ua.match(/Android/)));}var startUp=function(){mw.config=new mw.Map(true);mw.loader.addSource({"local":{"loadScript":"//bits.wikimedia.org/en.wikipedia.org/load.php","apiScript":"/w/api.php"},"metawiki":{"apiScript":"//meta.wikimedia.org/w/api.php","loadScript":"//bits.wikimedia.org/meta.wikimedia.org/load.php"}});mw.loader.register([["user.groups","1408373753",[],"user"],["user.options","1356998400",[],"private"],["user.tokens","1356998400",[],"private"],["mediawiki.language.data","1400164461",["mediawiki.language.init"]
],["mediawiki.skinning.content.parsoid","1356998400"],["jquery.accessKeyLabel","1409282827",["jquery.client","jquery.mwExtension"]],["jquery.autoEllipsis","1409252984",["jquery.highlightText"]],["jquery.byteLength","1409252983"],["jquery.byteLimit","1409252984",["jquery.byteLength"]],["jquery.checkboxShiftClick","1409425127"],["jquery.client","1409252983"],["jquery.cookie","1409252983"],["jquery.getAttrs","1409252984"],["jquery.hidpi","1409425063"],["jquery.highlightText","1409597691",["jquery.mwExtension"]],["jquery.json","1409252982"],["jquery.makeCollapsible","1409282827"],["jquery.mw-jump","1409529027"],["jquery.mwExtension","1409391966"],["jquery.placeholder","1409252975"],["jquery.qunit","1409252984"],["jquery.qunit.completenessTest","1409252984",["jquery.qunit"]],["json","1409252983",[],null,"local","return!!(window.JSON\u0026\u0026JSON.stringify\u0026\u0026JSON.parse);"],["mediawiki.api","1409252977",["mediawiki.util"]],["mediawiki.hidpi","1409290829",["jquery.hidpi"],null,
"local","return'srcset'in new Image();"],["mediawiki.inspect","1409252984",["jquery.byteLength","json"]],["mediawiki.notification","1409252984",["mediawiki.page.startup"]],["mediawiki.notify","1409252983"],["mediawiki.Title","1409252983",["jquery.byteLength","mediawiki.util"]],["mediawiki.toc","1409600640",["jquery.cookie"]],["mediawiki.Uri","1409252983",["mediawiki.util"]],["mediawiki.user","1409252977",["jquery.cookie","mediawiki.api","user.options","user.tokens"]],["mediawiki.util","1409252983",["jquery.accessKeyLabel","mediawiki.notify"]],["mediawiki.action.history.diff","1409252978",[],"mediawiki.action.history"],["mediawiki.language","1409252982",["mediawiki.cldr","mediawiki.language.data"]],["mediawiki.cldr","1409513476",["mediawiki.libs.pluralruleparser"]],["mediawiki.libs.pluralruleparser","1409252982"],["mediawiki.language.init","1409252982"],["mediawiki.jqueryMsg","1409252982",["mediawiki.language","mediawiki.util"]],["mediawiki.language.names","1406225848",[
"mediawiki.language.init"]],["mediawiki.page.ready","1409252978",["jquery.checkboxShiftClick","jquery.makeCollapsible","jquery.mw-jump","jquery.placeholder","mediawiki.util"]],["mediawiki.page.startup","1409252983",["mediawiki.util"]],["mediawiki.special.javaScriptTest","1409252984",["jquery.qunit"]],["mediawiki.ui","1409252980"],["mediawiki.ui.checkbox","1409252977"],["mediawiki.ui.anchor","1409252984"],["mediawiki.ui.button","1409252975"],["mediawiki.ui.input","1409252977"],["es5-shim","1409257988",[],null,"local","return(function(){'use strict';return!this\u0026\u0026!!Function.prototype.bind;}());"],["oojs","1409264721",["es5-shim","json"]],["oojs-ui","1409282827",["oojs"]],["schema.Popups","1364535356",["ext.eventLogging"]],["jquery.conditionalScroll","1409252984",[],"ext.flow"],["schema.GuidedTourGuiderImpression","1365692795",["ext.eventLogging"]],["schema.GuidedTourGuiderHidden","1365688949",["ext.eventLogging"]],["schema.GuidedTourButtonClick","1365688950",["ext.eventLogging"]
],["schema.GuidedTourInternalLinkActivation","1365688953",["ext.eventLogging"]],["schema.GuidedTourExternalLinkActivation","1365688960",["ext.eventLogging"]],["schema.GuidedTourExited","1365688966",["ext.eventLogging"]],["schema.MediaViewer","1365934062",["ext.eventLogging"]],["schema.MultimediaViewerNetworkPerformance","1364916296",["ext.eventLogging"]],["schema.MultimediaViewerDuration","1365571041",["ext.eventLogging"]],["ext.confirmEdit.fancyCaptcha.styles","1409252983"],["ext.confirmEdit.fancyCaptchaMobile","1409252984",["mobile.startup"]],["ext.dismissableSiteNotice","1409687939",["jquery.cookie","mediawiki.util"]],["ext.centralNotice.bannerController.mobiledevice","1409252984"],["ext.centralNotice.bannerController.mobile","1409252984",["ext.centralNotice.bannerController.mobiledevice","jquery.cookie","jquery.json"]],["ext.betaFeatures","1409252984",["jquery.client"]],["ext.parsoid.styles","1409252984"],["rangy","1409252984"],["jquery.visibleText","1409252984"],["Base64.js",
"1409252984"],["easy-deflate.core","1409252984",["Base64.js"]],["easy-deflate.deflate","1409252984",["easy-deflate.core"]],["unicodejs","1409252984"],["ext.visualEditor.mobileViewTarget","1409252984",["ext.visualEditor.core.mobile","ext.visualEditor.mediawiki","ext.visualEditor.mwimage.core"]],["ext.visualEditor.base","1409252984",["oojs-ui","unicodejs"]],["ext.visualEditor.mediawiki","1409252984",["easy-deflate.deflate","ext.visualEditor.base","jquery.visibleText","mediawiki.Title","mediawiki.Uri","mediawiki.user"]],["ext.visualEditor.data","1409282827",["ext.visualEditor.base"]],["ext.visualEditor.core","1409282827",["ext.visualEditor.base","jquery.uls.data","rangy"]],["ext.visualEditor.core.mobile","1409252984",["ext.visualEditor.core"]],["ext.visualEditor.mwcore","1409508377",["ext.visualEditor.core","jquery.autoEllipsis","jquery.byteLimit","mediawiki.Title","mediawiki.action.history.diff","mediawiki.jqueryMsg","mediawiki.skinning.content.parsoid","mediawiki.user"]],[
"ext.visualEditor.mwformatting","1409282828",["ext.visualEditor.mwcore"]],["ext.visualEditor.mwimage.core","1409252984",["ext.visualEditor.mwcore"]],["ext.visualEditor.mwimage","1409282828",["ext.visualEditor.mwimage.core"]],["ext.visualEditor.mwlink","1409282828",["ext.visualEditor.mwcore"]],["ext.visualEditor.mwmeta","1409282828",["ext.visualEditor.mwlink"]],["ext.visualEditor.mwreference.core","1409282828",["ext.visualEditor.mwcore"]],["ext.visualEditor.mwreference","1409282828",["ext.visualEditor.mwreference.core","ext.visualEditor.mwtransclusion"]],["ext.visualEditor.mwtransclusion.core","1409252984",["ext.visualEditor.mwcore"]],["ext.visualEditor.mwtransclusion","1409282828",["ext.visualEditor.mwtransclusion.core"]],["ext.visualEditor.language","1409282828",["ext.visualEditor.core","mediawiki.language.names"]],["ext.visualEditor.mwalienextension","1409252984",["ext.visualEditor.mwcore"]],["ext.visualEditor.mwgallery","1409282828",["ext.visualEditor.mwcore"]],[
"ext.visualEditor.experimental","1356998400",["ext.visualEditor.language","ext.visualEditor.mwalienextension"]],["ext.visualEditor.icons","1409252984"],["ext.mantle","1409252977"],["ext.mantle.modules","1409252984",["ext.mantle"]],["ext.mantle.templates","1409252977",["ext.mantle"]],["ext.mantle.hogan","1409252977",["ext.mantle.templates"]],["ext.mantle.handlebars","1409252984",["ext.mantle.templates"]],["ext.mantle.oo","1409252984",["ext.mantle.modules"]],["ext.mantle.views","1409252984",["ext.mantle.oo","ext.mantle.templates"]],["mobile.templates","1409252977",["ext.mantle.hogan"]],["mobile.bridge","1409252984"],["mobile.file.scripts","1409252984",["mobile.startup"]],["mobile.pagelist.styles","1409252975"],["mobile.pagelist.scripts","1409252977",["mobile.watchstar"]],["skins.minerva.tablet.styles","1409252975"],["mobile.toc","1409282827",["mobile.loggingSchemas","mobile.toggling"]],["tablet.scripts","1356998400",["mobile.toc"]],["skins.minerva.chrome.styles","1409252975"],[
"skins.minerva.content.styles","1409252975"],["skins.minerva.chrome.styles.beta","1409252984"],["skins.minerva.drawers.styles","1409252975"],["mobile.head","1409282827",["mediawiki.jqueryMsg"]],["mobile.startup","1409282827",["mobile.redlinks","mobile.templates","mobile.user"]],["mobile.redlinks","1409252977",["mediawiki.user","mobile.head"]],["mobile.user","1409252977",["mediawiki.user","mobile.head"]],["mobile.editor","1409252977",["mobile.stable.common"]],["mobile.editor.api","1409282827",["mobile.stable"]],["mobile.editor.common","1409282827",["mobile.editor.api"]],["mobile.editor.ve","1409282827",["ext.visualEditor.mobileViewTarget","mobile.editor.common"]],["mobile.editor.overlay","1409282827",["mobile.editor.common"]],["mobile.uploads","1409282827",["mobile.editor.api"]],["mobile.beta.common","1356998400",["mobile.loggingSchemas","mobile.stable.common"]],["mobile.geonotahack","1409282827",["mobile.loggingSchemas","mobile.stable.common"]],["mobile.talk","1409282827",[
"mobile.beta.common","mobile.stable"]],["mobile.beta","1409252984",["mobile.beta.common","mobile.stable"]],["mobile.search","1409282827",["mobile.pagelist.scripts"]],["mobile.talk.common","1409282827",["mobile.talk"]],["mobile.ajaxpages","1409252984",["mobile.startup"]],["mobile.mediaViewer","1409282827",["mobile.overlays"]],["mobile.alpha","1409252984",["mobile.beta"]],["mobile.toast.styles","1409252977"],["mobile.stable.styles","1409252977"],["mobile.overlays","1409282827",["mobile.startup"]],["mobile.stable.common","1409282827",["mobile.overlays","mobile.toast.styles"]],["mobile.references","1409252977",["mobile.stable.common"]],["mobile.toggling","1409252977",["mobile.startup"]],["mobile.contentOverlays","1409254996",["mobile.overlays"]],["mobile.newusers","1409282827",["mobile.contentOverlays","mobile.editor","mobile.loggingSchemas"]],["mobile.watchstar","1409282827",["mobile.stable.common"]],["mobile.stable","1409282827",["mobile.loggingSchemas","mobile.pagelist.scripts",
"mobile.references","mobile.stable.styles"]],["mobile.languages","1409282827",["mobile.overlays"]],["mobile.issues","1409282827",["mobile.overlays"]],["mobile.nearby","1409282827",["jquery.json","mobile.loggingSchemas","mobile.pagelist.scripts","mobile.special.nearby.styles"]],["mobile.notifications","1409252984",["mobile.overlays"]],["mobile.notifications.overlay","1409282828",["ext.echo.base","mobile.stable"]],["mobile.site","1409087722",[],"site"],["mobile.special.app.scripts","1409252984",["mobile.ajaxpages","mobile.search","mobile.stable.styles"]],["mobile.special.app.styles","1409252984"],["mobile.special.mobilemenu.styles","1409327988",[],"other"],["mobile.special.mobileoptions.styles","1409252984",[],"other"],["mobile.special.mobileoptions.scripts","1409282828",["mobile.startup"]],["mobile.special.mobileeditor.scripts","1409252984",[],"other"],["mobile.special.nearby.styles","1409252984"],["mobile.special.nearby.beta","1409282828",["mobile.beta.common","mobile.nearby"]],[
"mobile.special.nearby.scripts","1409282828",["mobile.nearby"]],["mobile.special.notifications.styles","1409252984",[],"other"],["mobile.special.notifications.scripts","1409282828",["mobile.stable"],"other"],["mobile.special.userprofile.styles","1409252984",[],"other"],["mobile.special.uploads.scripts","1409282828",["mobile.stable"]],["mobile.special.uploads.styles","1409252984",[],"other"],["mobile.special.pagefeed.styles","1409252984",[],"other"],["mobile.special.mobilediff.styles","1409252984",[],"other"],["mobile.special.mobilediff.scripts","1409252984",["mobile.loggingSchemas","mobile.stable.common"]],["skins.minerva.special.styles","1409252984",[],"other"],["skins.minerva.special.preferences.scripts","1409252984",[],"other"],["skins.minerva.special.search.styles","1409252984",[],"other"],["skins.minerva.special.watchlist.scripts","1409252984",["mobile.loggingSchemas","mobile.pagelist.scripts"],"other"],["skins.minerva.special.userlogin.styles","1409252984",[],"other"],[
"zerobanner.styles","1409252978"],["zerobanner.config.styles","1409506081"],["zerobanner","1409282827",["mobile.stable","zerobanner.styles"]],["zerobanner.special.scripts","1409252984"],["zerobanner.special.styles","1409252981"],["ext.math.visualEditor","1409282827",["ext.visualEditor.mwcore"]],["ext.echo.base","1409282827",["schema.Echo","schema.EchoInteraction","schema.EchoMail"]],["ext.thanks.mobilediff","1409282827",["mobile.special.mobilediff.scripts"]],["ext.flow.templating","1409250007",["ext.mantle.handlebars"],"ext.flow"],["ext.flow.mediawiki.ui.text","1409252984",[],"ext.flow"],["ext.flow.mediawiki.ui.form","1409252984",[],"ext.flow"],["ext.flow.mediawiki.ui.tooltips","1409252984",[],"ext.flow"],["ext.flow.icons.styles","1409252984",[],"ext.flow"],["ext.flow.styles","1409252984",[],"ext.flow"],["ext.flow.board.styles","1409252984",[],"ext.flow"],["ext.flow.board.topic.styles","1409252984",[],"ext.flow"],["ext.flow.new.handlebars","1409252984",["ext.mantle.handlebars"],
"ext.flow"],["ext.flow.new.history","1409252984",[],"ext.flow"],["ext.flow.new","1409282827",["ext.flow.editor","ext.flow.new.handlebars","ext.flow.new.history","ext.flow.templating","ext.flow.vendor.jquery.ba-throttle-debounce","ext.flow.vendor.storer","jquery.conditionalScroll","jquery.json","mediawiki.Title","mediawiki.Uri","mediawiki.jqueryMsg","mediawiki.user"],"ext.flow"],["ext.flow.vendor.storer","1409252984",[],"ext.flow"],["ext.flow.vendor.jquery.ba-throttle-debounce","1409252984",[],"ext.flow"],["ext.flow.editor","1409252984",["ext.flow.parsoid"],"ext.flow"],["ext.flow.editors.none","1409252984",[],"ext.flow"],["ext.flow.parsoid","1409252984",[],"ext.flow"],["ext.disambiguator.visualEditor","1409619534",["ext.visualEditor.mediawiki","ext.visualEditor.mwmeta"]],["schema.GettingStartedNavbarNoArticle","1362481517",["ext.eventLogging"]],["schema.GettingStartedRedirectImpression","1364353952",["ext.eventLogging"]],["schema.SignupExpPageLinkClick","1365963414",["ext.eventLogging"]
],["schema.SignupExpCTAImpression","1365963423",["ext.eventLogging"]],["schema.SignupExpCTAButtonClick","1365963428",["ext.eventLogging"]],["schema.TaskRecommendationLightbulbClick","1366431656",["ext.eventLogging"]],["schema.TaskRecommendationImpression","1366264626",["ext.eventLogging"]],["schema.TaskRecommendation","1366264719",["ext.eventLogging"]],["schema.TaskRecommendationClick","1366264717",["ext.eventLogging"]],["ext.eventLogging","1409252977",["json","mediawiki.util"]],["ext.eventLogging.subscriber","1409252975"],["ext.campaigns","1409252984",["jquery.cookie"]],["schema.TimingData","1364253208",["ext.eventLogging"]],["schema.DeprecatedUsage","1364904587",["ext.eventLogging"]],["schema.JQMigrateUsage","1365771847",["ext.eventLogging"]],["ext.wikimediaEvents.ve","1409252984",["ext.visualEditor.base"]],["ext.wikimediaEvents.deprecate","1409566502"],["schema.NavigationTiming","1365363652",["ext.eventLogging"]],["ext.navigationTiming","1409252977",["schema.NavigationTiming"]],[
"ext.uls.languagenames","1408444776"],["ext.uls.webfonts.repository","1409252984"],["ext.uls.webfonts.mobile","1409252984",["ext.uls.webfonts.repository","jquery.webfonts"]],["jquery.i18n","1409252982",["mediawiki.libs.pluralruleparser"]],["jquery.uls.data","1409252982"],["jquery.webfonts","1409252984"],["schema.UniversalLanguageSelector","1364325841",["ext.eventLogging"]],["skins.vector.compactPersonalBar.schema","1364827528",["ext.eventLogging"]],["mobile.uploads.schema","1365207443",["ext.eventLogging"]],["mobile.editing.schema","1365597425",["ext.eventLogging"]],["schema.MobileWebCta","1362971084",["ext.eventLogging"]],["schema.MobileWebClickTracking","1362928348",["ext.eventLogging"]],["mobile.loggingSchemas","1409252977",["mobile.editing.schema","mobile.startup","mobile.uploads.schema","schema.MobileWebClickTracking","schema.MobileWebCta"]],["schema.Echo","1364729716",["ext.eventLogging"]],["schema.EchoMail","1362466050",["ext.eventLogging"]],["schema.EchoInteraction",
"1362780687",["ext.eventLogging"]]]);mw.config.set({"wgLoadScript":"//bits.wikimedia.org/en.wikipedia.org/load.php","debug":false,"skin":"minerva","stylepath":"//bits.wikimedia.org/static-1.24wmf18/skins","wgUrlProtocols":"bitcoin\\:|ftp\\:\\/\\/|ftps\\:\\/\\/|geo\\:|git\\:\\/\\/|gopher\\:\\/\\/|http\\:\\/\\/|https\\:\\/\\/|irc\\:\\/\\/|ircs\\:\\/\\/|magnet\\:|mailto\\:|mms\\:\\/\\/|news\\:|nntp\\:\\/\\/|redis\\:\\/\\/|sftp\\:\\/\\/|sip\\:|sips\\:|sms\\:|ssh\\:\\/\\/|svn\\:\\/\\/|tel\\:|telnet\\:\\/\\/|urn\\:|worldwind\\:\\/\\/|xmpp\\:|\\/\\/","wgArticlePath":"/wiki/$1","wgScriptPath":"/w","wgScriptExtension":".php","wgScript":"/w/index.php","wgSearchType":"LuceneSearch","wgVariantArticlePath":false,"wgActionPaths":{},"wgServer":"//en.wikipedia.org","wgServerName":"en.wikipedia.org","wgUserLanguage":"en","wgContentLanguage":"en","wgVersion":"1.24wmf18","wgEnableAPI":true,"wgEnableWriteAPI":true,"wgMainPageTitle":"Main Page","wgFormattedNamespaces":{"-2":"Media","-1":"Special","0":"",
"1":"Talk","2":"User","3":"User talk","4":"Wikipedia","5":"Wikipedia talk","6":"File","7":"File talk","8":"MediaWiki","9":"MediaWiki talk","10":"Template","11":"Template talk","12":"Help","13":"Help talk","14":"Category","15":"Category talk","100":"Portal","101":"Portal talk","108":"Book","109":"Book talk","118":"Draft","119":"Draft talk","446":"Education Program","447":"Education Program talk","710":"TimedText","711":"TimedText talk","828":"Module","829":"Module talk","2600":"Topic"},"wgNamespaceIds":{"media":-2,"special":-1,"":0,"talk":1,"user":2,"user_talk":3,"wikipedia":4,"wikipedia_talk":5,"file":6,"file_talk":7,"mediawiki":8,"mediawiki_talk":9,"template":10,"template_talk":11,"help":12,"help_talk":13,"category":14,"category_talk":15,"portal":100,"portal_talk":101,"book":108,"book_talk":109,"draft":118,"draft_talk":119,"education_program":446,"education_program_talk":447,"timedtext":710,"timedtext_talk":711,"module":828,"module_talk":829,"topic":2600,"wp":4,"wt":5,"image":6,
"image_talk":7,"project":4,"project_talk":5},"wgContentNamespaces":[0],"wgSiteName":"Wikipedia","wgFileExtensions":["png","gif","jpg","jpeg","tiff","tif","xcf","pdf","mid","ogg","ogv","svg","djvu","oga","flac","wav","webm"],"wgDBname":"enwiki","wgFileCanRotate":true,"wgAvailableSkins":{"vector":"Vector","monobook":"MonoBook","modern":"Modern","cologneblue":"CologneBlue","minerva":"Minerva","fallback":"Fallback"},"wgExtensionAssetsPath":"//bits.wikimedia.org/static-1.24wmf18/extensions","wgCookiePrefix":"enwiki","wgCookieDomain":"","wgCookiePath":"/","wgCookieExpiration":2592000,"wgResourceLoaderMaxQueryLength":-1,"wgCaseSensitiveNamespaces":[],"wgLegalTitleChars":" %!\"$\u0026'()*,\\-./0-9:;=?@A-Z\\\\\\^_`a-z~+\\u0080-\\uFFFF","wgResourceLoaderStorageVersion":1,"wgResourceLoaderStorageEnabled":true,"EmbedPlayer.DirectFileLinkWarning":true,"EmbedPlayer.EnableOptionsMenu":true,"EmbedPlayer.DisableJava":false,"EmbedPlayer.DisableHTML5FlashFallback":true,"TimedText.ShowInterface":"always",
"TimedText.ShowAddTextLink":true,"EmbedPlayer.WebPath":"//bits.wikimedia.org/static-1.24wmf18/extensions/TimedMediaHandler/MwEmbedModules/EmbedPlayer","wgCortadoJarFile":false,"AjaxRequestTimeout":30,"MediaWiki.DefaultProvider":"local","MediaWiki.ApiProviders":{"wikimediacommons":{"url":"//commons.wikimedia.org/w/api.php"}},"MediaWiki.ApiPostActions":["login","purge","rollback","delete","undelete","protect","block","unblock","move","edit","upload","emailuser","import","userrights"],"EmbedPlayer.OverlayControls":true,"EmbedPlayer.CodecPreference":["webm","h264","ogg"],"EmbedPlayer.DisableVideoTagSupport":false,"EmbedPlayer.ReplaceSources":null,"EmbedPlayer.EnableFlavorSelector":false,"EmbedPlayer.EnableIpadHTMLControls":true,"EmbedPlayer.WebKitPlaysInline":false,"EmbedPlayer.EnableIpadNativeFullscreen":false,"EmbedPlayer.iPhoneShowHTMLPlayScreen":true,"EmbedPlayer.ForceLargeReplayButton":false,"EmbedPlayer.LibraryPage":
"http://www.kaltura.org/project/HTML5_Video_Media_JavaScript_Library","EmbedPlayer.RewriteSelector":"video,audio,playlist","EmbedPlayer.DefaultSize":"400x300","EmbedPlayer.ControlsHeight":31,"EmbedPlayer.TimeDisplayWidth":85,"EmbedPlayer.KalturaAttribution":true,"EmbedPlayer.AttributionButton":{"title":"Kaltura html5 video library","href":"http://www.kaltura.com","class":"kaltura-icon","style":[],"iconurl":false},"EmbedPlayer.EnableRightClick":true,"EmbedPlayer.EnabledOptionsMenuItems":["playerSelect","download","share","aboutPlayerLibrary"],"EmbedPlayer.WaitForMeta":true,"EmbedPlayer.ShowNativeWarning":true,"EmbedPlayer.ShowPlayerAlerts":true,"EmbedPlayer.EnableFullscreen":true,"EmbedPlayer.EnableTimeDisplay":true,"EmbedPlayer.EnableVolumeControl":true,"EmbedPlayer.NewWindowFullscreen":false,"EmbedPlayer.FullscreenTip":true,"EmbedPlayer.FirefoxLink":"http://www.mozilla.com/en-US/firefox/upgrade.html?from=mwEmbed","EmbedPlayer.NativeControls":false,
"EmbedPlayer.NativeControlsMobileSafari":true,"EmbedPlayer.FullScreenZIndex":999998,"EmbedPlayer.ShareEmbedMode":"iframe","EmbedPlayer.SkinList":["mvpcf","kskin"],"EmbedPlayer.DefaultSkin":"mvpcf","EmbedPlayer.MonitorRate":250,"EmbedPlayer.UseFlashOnAndroid":false,"EmbedPlayer.EnableURLTimeEncoding":"flash","EmbedPLayer.IFramePlayer.DomainWhiteList":"*","EmbedPlayer.EnableIframeApi":true,"EmbedPlayer.PageDomainIframe":true,"EmbedPlayer.NotPlayableDownloadLink":true,"EmbedPlayer.BlackPixel":"data:image/png,%89PNG%0D%0A%1A%0A%00%00%00%0DIHDR%00%00%00%01%00%00%00%01%08%02%00%00%00%90wS%DE%00%00%00%01sRGB%00%AE%CE%1C%E9%00%00%00%09pHYs%00%00%0B%13%00%00%0B%13%01%00%9A%9C%18%00%00%00%07tIME%07%DB%0B%0A%17%041%80%9B%E7%F2%00%00%00%19tEXtComment%00Created%20with%20GIMPW%81%0E%17%00%00%00%0CIDAT%08%D7c%60%60%60%00%00%00%04%00%01'4'%0A%00%00%00%00IEND%AEB%60%82","TimedText.ShowRequestTranscript":false,"TimedText.NeedsTranscriptCategory":"Videos needing subtitles","TimedText.BottomPadding":10,
"TimedText.BelowVideoBlackBoxHeight":40,"wgCentralAuthCheckLoggedInURL":"//login.wikimedia.org/wiki/Special:CentralAutoLogin/checkLoggedIn?type=script\u0026wikiid=enwiki","wgWikiEditorMagicWords":{"redirect":"#REDIRECT","img_right":"right","img_left":"left","img_none":"none","img_center":"center","img_thumbnail":"thumbnail","img_framed":"framed","img_frameless":"frameless"},"wgMultimediaViewer":{"infoLink":"//mediawiki.org/wiki/Special:MyLanguage/Multimedia/About_Media_Viewer","discussionLink":"//mediawiki.org/wiki/Special:MyLanguage/Talk:Multimedia/About_Media_Viewer","helpLink":"//mediawiki.org/wiki/Special:MyLanguage/Multimedia/Media_Viewer/Help","globalUsageAvailable":true,"useThumbnailGuessing":true,"durationSamplingFactor":1000,"networkPerformanceSamplingFactor":1000,"actionLoggingSamplingFactorMap":{"default":10,"close":4000,"defullscreen":45,"enlarge":40,"fullscreen":80,"hash-load":100,"history-navigation":1600,"image-view":10000,"metadata-close":80,"metadata-open":70,
"next-image":2500,"prev-image":1000,"thumbnail":5000},"tooltipDelay":1000},"wgMediaViewer":true,"wgMediaViewerIsInBeta":false,"wgVisualEditorConfig":{"disableForAnons":false,"preferenceModules":{"visualeditor-enable-experimental":"ext.visualEditor.experimental","visualeditor-enable-language":"ext.visualEditor.language"},"namespaces":[0,2,100,108,118,2,6,12,14],"pluginModules":["ext.math.visualEditor","ext.wikimediaEvents.ve","ext.disambiguator.visualEditor"],"defaultUserOptions":{"betatempdisable":0,"enable":0,"defaultthumbsize":220,"visualeditor-enable-experimental":0,"visualeditor-enable-language":0},"blacklist":{"msie":null,"android":[["\u003C",3]],"firefox":[["\u003C=",14]],"opera":[["\u003C",12]],"blackberry":null,"silk":null},"skins":["vector","apex","monobook","minerva"],"tabPosition":"after","tabMessages":{"edit":null,"editsource":"visualeditor-ca-editsource","create":null,"createsource":"visualeditor-ca-createsource","editlocaldescriptionsource":
"visualeditor-ca-editlocaldescriptionsource","createlocaldescriptionsource":"visualeditor-ca-createlocaldescriptionsource","editsection":null,"editsectionsource":"visualeditor-ca-editsource-section","editappendix":"visualeditor-beta-appendix","editsourceappendix":null,"createappendix":"visualeditor-beta-appendix","createsourceappendix":null,"editsectionappendix":"visualeditor-beta-appendix","editsectionsourceappendix":null},"showBetaWelcome":true,"enableTocWidget":false},"wgStopMobileRedirectCookie":{"name":"stopMobileRedirect","duration":30,"domain":".wikipedia.org","path":"/"},"wgMFNearbyEndpoint":"","wgMFContentNamespace":0,"wgMFLicenseLink":"\u003Ca href=\"https://creativecommons.org/licenses/by-sa/3.0/\" title=\"Definition of the Creative Commons Attribution-ShareAlike License\" target=\"_blank\"\u003ECC BY-SA 3.0\u003C/a\u003E and \u003Ca href=\"https://www.gnu.org/licenses/fdl.html\" title=\"Definition of the GFDL\" target=\"_blank\"\u003EGFDL\u003C/a\u003E",
"wgMFUploadLicenseLink":"\u003Ca class=\"external\" rel=\"nofollow\" href=\"//creativecommons.org/licenses/by-sa/3.0/\"\u003ECC BY-SA 3.0\u003C/a\u003E","wgPageTriageCurationModules":{"articleInfo":{"helplink":"//en.wikipedia.org/wiki/Wikipedia:Page_Curation/Help#PageInfo","namespace":[0,2]},"wikiLove":{"helplink":"//en.wikipedia.org/wiki/Wikipedia:Page_Curation/Help#WikiLove","namespace":[0,2]},"mark":{"helplink":"//en.wikipedia.org/wiki/Wikipedia:Page_Curation/Help#MarkReviewed","namespace":[0,2],"note":[0]},"tags":{"helplink":"//en.wikipedia.org/wiki/Wikipedia:Page_Curation/Help#AddTags","namespace":[0]},"delete":{"helplink":"//en.wikipedia.org/wiki/Wikipedia:Page_Curation/Help#MarkDeletion","namespace":[0,2]}},"wgPageTriageNamespaces":[0,2],"wgTalkPageNoteTemplate":{"Mark":"Reviewednote-NPF","UnMark":{"note":"Unreviewednote-NPF","nonote":"Unreviewednonote-NPF"},"Tags":"Taggednote-NPF"},"wgFlowEditorList":["none"],"wgFlowMaxTopicLength":260,"wgFlowPageSize":{"expanded":10,
"collapsed-full":20,"collapsed-oneline":30},"wgGettingStartedConfig":{"hasCategories":true},"wgEventLoggingBaseUri":"//bits.wikimedia.org/event.gif","wgNavigationTimingSamplingFactor":1000,"wgULSIMEEnabled":false,"wgULSWebfontsEnabled":false,"wgULSPosition":"interlanguage","wgULSAnonCanChangeLanguage":false,"wgULSEventLogging":true,"wgULSImeSelectors":["input:not([type])","input[type=text]","input[type=search]","textarea","[contenteditable]"],"wgULSNoImeSelectors":["#wpCaptchaWord",".ve-ce-documentNode",".ace_editor textarea"],"wgULSNoWebfontsSelectors":["#p-lang li.interlanguage-link \u003E a"],"wgULSFontRepositoryBasePath":"//bits.wikimedia.org/static-current/extensions/UniversalLanguageSelector/data/fontrepo/fonts/","wgNoticeFundraisingUrl":"https://donate.wikimedia.org/wiki/Special:LandingCheck","wgCentralPagePath":"//meta.wikimedia.org/w/index.php","wgCentralBannerDispatcher":"//meta.wikimedia.org/wiki/Special:BannerRandom","wgCentralBannerRecorder":
"//meta.wikimedia.org/wiki/Special:RecordImpression","wgNoticeXXCountries":["XX","EU","AP","A1","A2","O1"],"wgNoticeNumberOfBuckets":4,"wgNoticeBucketExpiry":7,"wgNoticeNumberOfControllerBuckets":2,"wgNoticeCookieDurations":{"close":1209600,"donate":25920000},"wgNoticeHideUrls":["//en.wikipedia.org/wiki/Special:HideBanners","//meta.wikimedia.org/wiki/Special:HideBanners","//commons.wikimedia.org/wiki/Special:HideBanners","//species.wikimedia.org/wiki/Special:HideBanners","//en.wikibooks.org/wiki/Special:HideBanners","//en.wikiquote.org/wiki/Special:HideBanners","//en.wikisource.org/wiki/Special:HideBanners","//en.wikinews.org/wiki/Special:HideBanners","//en.wikiversity.org/wiki/Special:HideBanners","//www.mediawiki.org/wiki/Special:HideBanners"]});};if(isCompatible()){document.write(
"\u003Cscript src=\"//bits.wikimedia.org/en.wikipedia.org/load.php?debug=false\u0026amp;lang=en\u0026amp;modules=jquery%2Cmediawiki\u0026amp;only=scripts\u0026amp;skin=minerva\u0026amp;version=20140828T190944Z\"\u003E\u003C/script\u003E");};
/* cache key: enwiki:resourceloader:filter:minify-js:7:06ef7255a4be01eee2a8aa153704f58b */
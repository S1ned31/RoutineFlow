package com.busol.habittracker.core.data.ui


//@Composable
//internal fun HabitAlertScreen(
//    errorData: com.busol.habittracker.core.ui.components.ErrorData,
//    onActionClick: (String) -> Unit,
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(AppTheme.colors.background)
//            .padding(AppTheme.dimensions.paddingDefault),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Box(
//            modifier = Modifier
//                .clip(RoundedCornerShape(50.dp))
//                .background(
//                    color = AppTheme.colors.alertColor.copy(alpha = 0.2f),
//                ), contentAlignment = Alignment.Center
//        ) {
//            Icon(
//                modifier = Modifier.padding(20.dp),
//                imageVector = IconAlertCircle,
//                tint = AppTheme.colors.alertColor,
//                contentDescription = "error"
//            )
//        }
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        HabitText(
//            text = errorData.title,
//            style = AppTheme.fonts.textFieldTitle,
//        )
//        Spacer(modifier = Modifier.height(6.dp))
//        errorData.subtitle?.also { subtitle ->
//            HabitText(
//                text = subtitle,
//                style = AppTheme.fonts.label,
//                textAlign = TextAlign.Center
//            )
//        }
//        errorData.button?.also { button ->
//            Spacer(modifier = Modifier.height(40.dp))
//            HabitButton(
//                modifier = Modifier,
//                text = button.text,
//                onClick = {
//                    onActionClick(button.action)
//                }
//            )
//        }
//    }
//}
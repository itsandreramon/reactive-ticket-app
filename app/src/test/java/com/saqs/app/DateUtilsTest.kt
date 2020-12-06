package com.saqs.app

import com.saqs.app.util.DateUtils.toLocalFormattedDate
import com.saqs.app.util.DateUtils.toUtcString
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.TemporalAccessor
import java.util.*


class DateUtilsTest : FunSpec({
    test("toLocalFormattedDateTest") {
        val zoneId = ZoneId.of("Europe/Berlin")
        val expected = ZonedDateTime.of(2020, 12, 6, 13, 0, 0, 0, zoneId)
        val instant = expected.toInstant()

        val actual = toLocalFormattedDate(instant, zoneId)
        System.out.println("Test instant " + Instant.now())
        actual shouldBe expected.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy"))
    }

    test("toUtcStringTest") {

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        val timestamp = "2020-12-06 15:20"
        val temporalAccessor: TemporalAccessor = formatter.parse(timestamp)
        val localDateTime = LocalDateTime.from(temporalAccessor)
        val zonedDateTime = ZonedDateTime.of(localDateTime, ZoneOffset.UTC)
        val expected = Instant.from(zonedDateTime)

        val instant = Instant.now()
        val actual = toUtcString(instant)


        /**val zoneId = ZoneId.of("Europe/Berlin")

        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(Locale.GERMANY)
                .withZone(zoneId)
        val instant = Instant.now()
        val expected: LocalDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC)


        //val expected = ZonedDateTime.of(2020, 12, 6, 13, 0, 0, 0, zoneId)

        val actual = toUtcString(instant)
        //val instant: Instant = Instant.now();*/
        System.out.println(expected)

       actual shouldBe  expected

    }

})

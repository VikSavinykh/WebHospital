package controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.record.Record;
import domain.record.RecordForm;
import domain.user.CurrentUser;
import service.record.RecordService;

@Controller
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @RequestMapping(value = "/pick-doctor", method = RequestMethod.GET)
    public ModelAndView getPickDoctorPage() {
        ModelAndView modelAndView = new ModelAndView("pick_doctor");
        modelAndView.addObject("day", new Date());
        return modelAndView;
    }

    @RequestMapping(value = "/pick-doctor", method = RequestMethod.POST)
    public ModelAndView pickDoctor(CurrentUser currentUser,
                                   @ModelAttribute RecordForm recordForm, RedirectAttributes redirectAttributes) throws ParseException {
        boolean isPastDay = new DateTime().withTime(0,0,0,0)
            .isAfter(new DateTime(recordForm.getDayDate()));
        boolean isWeekend = new DateTime(recordForm.getDayDate())
            .getDayOfWeek() == DateTimeConstants.SUNDAY
            || new DateTime(recordForm.getDayDate())
                .getDayOfWeek() == DateTimeConstants.SATURDAY;
        if (isPastDay) {
            ModelAndView modelAndView = new ModelAndView("redirect:/record/pick-doctor");
            redirectAttributes.addFlashAttribute("message", "Вы выбрали прошедший день!");
            return modelAndView;
        }
        if (isWeekend) {
            ModelAndView modelAndView = new ModelAndView("redirect:/record/pick-doctor");
            redirectAttributes.addFlashAttribute("message", "Вы выбрали выходной день!");
            return modelAndView;
        }

        List<Record> busyRecordForDoctorToday = recordService
                .findAllByDoctorAndDay(recordForm.getDoctor(), recordForm.getDayDate());
        List<RecordForm> allRecords = recordService.getAllRecordForms(recordForm.getDayDate(),
                busyRecordForDoctorToday, recordForm.getDoctor(),
                currentUser.getUser().getPatient());
        if(allRecords.stream().filter(record -> !record.isBusy()).count() == 0){
            ModelAndView modelAndView = new ModelAndView("redirect:/record/pick-doctor");
            redirectAttributes.addFlashAttribute("message", "На выбранную дату номерков нету!");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("pick_time");
        modelAndView.addObject("records",
                allRecords);

        modelAndView.addObject("doctor", recordForm.getDoctor());
        modelAndView.addObject("dayDate", recordForm.getDayDate());

        return modelAndView;
    }

    @RequestMapping(value = "/make-record", method = RequestMethod.POST)
    public ModelAndView makeRecord(CurrentUser currentUser, @ModelAttribute RecordForm record) {
        record.setPatient(currentUser.getUser().getPatient());
        recordService.create(record);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("message", "Спасибо, вы записаны к доктору!");
        return modelAndView;
    }

    @RequestMapping(value = "/user-records", method = RequestMethod.GET)
    public ModelAndView userRecord() {
        return new ModelAndView("user_records");
    }
}
